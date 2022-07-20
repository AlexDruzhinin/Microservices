cd ..\MOEXService\MOEXService
docker build -t moexservice .
cd ..\..\HistoricalService\HistoricalService
docker build -t historicalservice .
cd ..\..\UserPortal\UserPortal
docker build -t userportal .
cd ..\..\docker