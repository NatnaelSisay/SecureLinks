
# Load environment and create package in target
docker-compose down

source .env && docker-compose up db --build -d && \
source .env && ./mvnw clean package \
  -DGIT_CLIENT_ID=$GIT_CLIENT_ID \
  -DGIT_CLIENT_SECRET=$GIT_CLIENT_SECRET \
  -DGOOGLE_CLIENT_ID=$GOOGLE_CLIENT_ID \
  -DGOOGLE_CLIENT_SECRET=$GOOGLE_CLIENT_SECRET \
  -DPG_URL=$PG_URL \
  -DPG_PASSWORD=$PG_PASSWORD \
  -DPG_USER=$PG_USER

docker-compose down
