
#!/bin/bash

BASE_URL="http://localhost:8080"

# Examples:
# ./08-search-offers.sh
# ./08-search-offers.sh SENT
# ./08-search-offers.sh SENT CUSTOMER-123

STATUS="$1"
CUSTOMER_REFERENCE="$2"

URL="$BASE_URL/offers"

if [ -n "$STATUS" ]; then
  URL="$URL?status=$STATUS"
fi

if [ -n "$CUSTOMER_REFERENCE" ]; then
  if [[ "$URL" == *"?"* ]]; then
    URL="$URL&customerReference=$CUSTOMER_REFERENCE"
  else
    URL="$URL?customerReference=$CUSTOMER_REFERENCE"
  fi
fi

curl -i "$URL"
