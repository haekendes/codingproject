
#!/bin/bash

BASE_URL="http://localhost:8080"

echo "Alle Angebote:"
curl -i "$BASE_URL/offers"

echo -e "\nAngebote mit Status SENT:"
curl -i "$BASE_URL/offers?status=SENT"

echo -e "\nAngebote für CUSTOMER-123:"
curl -i "$BASE_URL/offers?customerReference=CUSTOMER-123"

echo -e "\nKombinierte Suche, Status ACCEPTED:"
curl -i "$BASE_URL/offers?status=ACCEPTED&customerReference=CUSTOMER-123"
