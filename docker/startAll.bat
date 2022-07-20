docker network create msvc-network
docker run --name HistoricalServiceDB --network msvc-network -e POSTGRES_PASSWORD=AIClab! -e POSTGRES_DB=HistoricalServiceDB -d postgres:14
docker run --name moexservice --network msvc-network -d moexservice
docker run --name historicalservice --network msvc-network -d historicalservice
docker run --name userportal -p 8080:8080 --network msvc-network -d userportal