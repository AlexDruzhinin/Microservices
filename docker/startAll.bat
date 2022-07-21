docker network create msvc-network
docker run --name historicalservicedb --network msvc-network -d ragnamir88/testmicroservice:historicalservicedb
docker run --name moexservice --network msvc-network -d ragnamir88/testmicroservice:moexservice
docker run --name historicalservice --network msvc-network -d ragnamir88/testmicroservice:historicalservice
docker run --name userportal -p 8080:8080 --network msvc-network -d ragnamir88/testmicroservice:userportal