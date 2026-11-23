
#!/bin/bash

BASE_URL="http://localhost:8080"
OFFER_ID="7c8c6f80-d573-4b33-94e9-f94ff2181151"

if [ -z "$OFFER_ID" ]; then
  echo "Usage: ./07-reject-offer.sh <OFFER_ID>"
  exit 1
fi

curl -i -X PATCH "$BASE_URL/offers/$OFFER_ID/reject"
