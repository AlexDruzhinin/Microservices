cd ..\MOEXService\MOEXService
docker build -t ragnamir88/testmicroservice:moexservice .
cd ..\..\HistoricalService\HistoricalService
docker build -t ragnamir88/testmicroservice:historicalservice .
cd ..\..\UserPortal\UserPortal
docker build -t ragnamir88/testmicroservice:userportal .
cd ..\..\DB
docker build -t ragnamir88/testmicroservice:historicalservicedb .
cd ..\docker