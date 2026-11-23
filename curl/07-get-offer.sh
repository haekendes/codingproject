
#!/bin/bash

BASE_URL="http://localhost:8080"
OFFER_ID="HIER-UUID-EINTRAGEN"

curl -i "$BASE_URL/offers/$OFFER_ID"
