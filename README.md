#Shipment Bundler
@author Vasily Rassokhin

##Desirable Improvements
* test and compare more shortest path algorithms. Floyd-Warshall was tested and was definitely too slow (947ms vs 
198113ms for thousand_shipments)
* add a mocking framework and create mocks in unit tests to isolate the tested component(s)
* write integration tests that can take sample files as input 
* write more thorough assertions in ScheduleGraphFactoryTest
* add a strategy pattern for using different shortest path algorithm implementations as desired
* implement a common TestBase parent that does more set up