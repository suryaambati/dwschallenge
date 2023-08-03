# dwschallenge
1.Added components like TransferController, TransferService, TransferRepositorty 
2.implement AmountTransfer Fuctionality with thredsafe and Email Functionality in TransferService Class
3.stroing the input data in TransferRepostory for Audit purpose
4.Handling Exceptions using AmountTransferException 
5.Added TransferControllerTest Class to Test multiple scenarios


//improvement needed for production deployment

1. Adding controllerAdvise class  to Handle Global Exceptions 
2. Need  to Add security to providing access of API for Authorized Users
3. Need to Add Load balancers for handling more number of requests on one time
4. Add more Test Cases for Code Coverage
