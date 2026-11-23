
#!/bin/bash

BASE_URL="http://localhost:8080"
OFFER_ID="7e79f097-76dd-4c8e-bf2f-30298ce8f94b"

if [ -z "$OFFER_ID" ]; then
  echo "Usage: ./06-accept-offer.sh <OFFER_ID>"
  exit 1
fi

curl -i -X PATCH "$BASE_URL/offers/$OFFER_ID/accept"
