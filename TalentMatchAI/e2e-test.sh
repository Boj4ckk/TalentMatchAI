#!/bin/bash

BASE_URL="http://localhost:8080"
BOLD="\033[1m"
GREEN="\033[0;32m"
CYAN="\033[0;36m"
YELLOW="\033[1;33m"
RESET="\033[0m"

echo -e "${BOLD}========================================${RESET}"
echo -e "${BOLD}   TalentMatchAI - Test E2E Pipeline    ${RESET}"
echo -e "${BOLD}========================================${RESET}\n"

# ─── 1. Créer un candidat ───────────────────────────────────────────────────
echo -e "${CYAN}[1/6] Création du candidat...${RESET}"
CANDIDATE=$(curl -s -X POST "$BASE_URL/candidates" \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "Alice",
    "lastName": "Dupont",
    "email": "alice.dupont@example.com",
    "githubUsername": "alice-dev",
    "skills": ["Java", "Spring Boot", "PostgreSQL", "Docker", "Kafka"],
    "yearsOfExperience": 4,
    "bio": "Développeuse backend passionnée par les architectures microservices et le cloud."
  }')

echo "$CANDIDATE" | python3 -m json.tool 2>/dev/null || echo "$CANDIDATE"
CANDIDATE_ID=$(echo "$CANDIDATE" | grep -o '"id":[^,}]*' | head -1 | cut -d: -f2 | tr -d ' "')
echo -e "${GREEN}✓ Candidat créé - ID: $CANDIDATE_ID${RESET}\n"

# ─── 2. Créer 3 offres d'emploi ─────────────────────────────────────────────
echo -e "${CYAN}[2/6] Création des offres d'emploi...${RESET}"

JOB1=$(curl -s -X POST "$BASE_URL/job-offers" \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Développeur Backend Java/Spring",
    "company": "TechCorp",
    "requiredSkills": ["Java", "Spring Boot", "PostgreSQL", "Docker"],
    "description": "Nous cherchons un développeur backend expérimenté pour construire des APIs REST scalables.",
    "location": "Paris",
    "salaryRange": "45k-55k"
  }')
JOB1_ID=$(echo "$JOB1" | grep -o '"id":[^,}]*' | head -1 | cut -d: -f2 | tr -d ' "')
echo -e "${GREEN}✓ Offre 1 créée (Backend Java) - ID: $JOB1_ID${RESET}"

JOB2=$(curl -s -X POST "$BASE_URL/job-offers" \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Ingénieur Data / Kafka",
    "company": "DataFlow",
    "requiredSkills": ["Kafka", "Python", "Spark", "Hadoop"],
    "description": "Rejoignez notre équipe data pour construire des pipelines temps réel avec Kafka et Spark.",
    "location": "Lyon",
    "salaryRange": "50k-65k"
  }')
JOB2_ID=$(echo "$JOB2" | grep -o '"id":[^,}]*' | head -1 | cut -d: -f2 | tr -d ' "')
echo -e "${GREEN}✓ Offre 2 créée (Data/Kafka) - ID: $JOB2_ID${RESET}"

JOB3=$(curl -s -X POST "$BASE_URL/job-offers" \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Développeur Mobile React Native",
    "company": "AppStudio",
    "requiredSkills": ["React Native", "TypeScript", "Redux", "iOS", "Android"],
    "description": "Développement d'\''applications mobiles cross-platform pour nos clients grands comptes.",
    "location": "Bordeaux",
    "salaryRange": "40k-50k"
  }')
JOB3_ID=$(echo "$JOB3" | grep -o '"id":[^,}]*' | head -1 | cut -d: -f2 | tr -d ' "')
echo -e "${GREEN}✓ Offre 3 créée (Mobile) - ID: $JOB3_ID${RESET}\n"

# ─── 3. Lancer le matching ───────────────────────────────────────────────────
echo -e "${CYAN}[3/6] Lancement des analyses de matching...${RESET}"

M1=$(curl -s -X POST "$BASE_URL/matching/analyze" \
  -H "Content-Type: application/json" \
  -d "{\"candidateId\": $CANDIDATE_ID, \"jobOfferId\": $JOB1_ID}")
M1_ID=$(echo "$M1" | grep -o '"id":[^,}]*' | head -1 | cut -d: -f2 | tr -d ' "')
echo -e "${GREEN}✓ Matching 1 lancé (Alice ↔ Backend Java) - ID: $M1_ID${RESET}"

M2=$(curl -s -X POST "$BASE_URL/matching/analyze" \
  -H "Content-Type: application/json" \
  -d "{\"candidateId\": $CANDIDATE_ID, \"jobOfferId\": $JOB2_ID}")
M2_ID=$(echo "$M2" | grep -o '"id":[^,}]*' | head -1 | cut -d: -f2 | tr -d ' "')
echo -e "${GREEN}✓ Matching 2 lancé (Alice ↔ Data/Kafka)  - ID: $M2_ID${RESET}"

M3=$(curl -s -X POST "$BASE_URL/matching/analyze" \
  -H "Content-Type: application/json" \
  -d "{\"candidateId\": $CANDIDATE_ID, \"jobOfferId\": $JOB3_ID}")
M3_ID=$(echo "$M3" | grep -o '"id":[^,}]*' | head -1 | cut -d: -f2 | tr -d ' "')
echo -e "${GREEN}✓ Matching 3 lancé (Alice ↔ Mobile)      - ID: $M3_ID${RESET}\n"

# ─── 4. Attendre les résultats ───────────────────────────────────────────────
echo -e "${CYAN}[4/6] Attente des résultats Ollama (max 60s)...${RESET}"

wait_for_result() {
  local id=$1
  local label=$2
  local max_wait=60
  local elapsed=0
  while [ $elapsed -lt $max_wait ]; do
    STATUS=$(curl -s "$BASE_URL/matching/results/$id" | grep -o '"status":"[^"]*"' | cut -d'"' -f4)
    if [ "$STATUS" = "COMPLETED" ] || [ "$STATUS" = "FAILED" ]; then
      echo -e "${GREEN}✓ $label terminé (status: $STATUS)${RESET}"
      return 0
    fi
    sleep 3
    elapsed=$((elapsed + 3))
    echo -e "  ⏳ $label en cours... ($elapsed s)"
  done
  echo -e "${YELLOW}⚠ $label timeout après ${max_wait}s${RESET}"
}

wait_for_result "$M1_ID" "Matching 1 (Backend Java)"
wait_for_result "$M2_ID" "Matching 2 (Data/Kafka)"
wait_for_result "$M3_ID" "Matching 3 (Mobile)"

# ─── 5. Afficher tous les résultats du candidat ─────────────────────────────
echo -e "\n${CYAN}[5/6] Résultats pour Alice (candidat $CANDIDATE_ID)...${RESET}"
curl -s "$BASE_URL/matching/candidate/$CANDIDATE_ID" | python3 -m json.tool 2>/dev/null

# ─── 6. Résumé ──────────────────────────────────────────────────────────────
echo -e "\n${CYAN}[6/6] Résumé des scores :${RESET}"

get_score() {
  local id=$1
  curl -s "$BASE_URL/matching/results/$id" | grep -o '"score":[^,}]*' | cut -d: -f2 | tr -d ' '
}

S1=$(get_score "$M1_ID")
S2=$(get_score "$M2_ID")
S3=$(get_score "$M3_ID")

echo -e "${BOLD}"
echo "  Offre 1 - Backend Java/Spring (TechCorp)  : ${S1}/100"
echo "  Offre 2 - Ingénieur Data/Kafka (DataFlow) : ${S2}/100"
echo "  Offre 3 - Mobile React Native (AppStudio) : ${S3}/100"
echo -e "${RESET}"
echo -e "${GREEN}Pipeline E2E terminée !${RESET}"
