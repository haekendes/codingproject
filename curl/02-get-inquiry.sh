
#!/bin/bash

BASE_URL="http://localhost:8080"
INQUIRY_ID="$1"

if [ -z "$INQUIRY_ID" ]; then
  echo "Usage: ./02-get-inquiry.sh <INQUIRY_ID>"
  exit 1
fi

curl -i "$BASE_URL/inquiries/$INQUIRY_ID"
