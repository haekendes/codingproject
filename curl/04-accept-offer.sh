
#!/bin/bash

BASE_URL="http://localhost:8080"
OFFER_ID="$1"

if [ -z "$OFFER_ID" ]; then
  echo "Usage: ./06-accept-offer.sh <OFFER_ID>"
  exit 1
fi

curl -i -X PATCH "$BASE_URL/offers/$OFFER_ID/accept"
