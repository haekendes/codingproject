
#!/bin/bash

BASE_URL="http://localhost:8080"

echo "Alle Angebote:"
curl -i "$BASE_URL/offers"

echo -e "\nAngebote mit Status DRAFT:"
curl -i "$BASE_URL/offers?status=DRAFT"

echo -e "\nAngebote für CUSTOMER-123:"
curl -i "$BASE_URL/offers?customerReference=CUSTOMER-123"

echo -e "\nKombinierte Suche:"
curl -i "$BASE_URL/offers?status=DRAFT&customerReference=CUSTOMER-123"
