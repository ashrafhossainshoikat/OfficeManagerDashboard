package services.Email;


public class ServiceSoapProxy implements services.Email.ServiceSoap {
  private String _endpoint = null;
  private services.Email.ServiceSoap serviceSoap = null;
  
  public ServiceSoapProxy() {
    _initServiceSoapProxy();
  }
  
  public ServiceSoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initServiceSoapProxy();
  }
  
  private void _initServiceSoapProxy() {
    try {
      serviceSoap = (new services.Email.ServiceLocator()).getServiceSoap();
      if (serviceSoap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)serviceSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)serviceSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (serviceSoap != null)
      ((javax.xml.rpc.Stub)serviceSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public services.Email.ServiceSoap getServiceSoap() {
    if (serviceSoap == null)
      _initServiceSoapProxy();
    return serviceSoap;
  }
  
  public String sendMailWithName(String sendFromName, String sendFromEmail, String sendToName, String sendToEmail, String subject, String mailBody) throws java.rmi.RemoteException {
	  return sendMail(sendFromName+" <"+sendFromEmail+">", sendToName+" <"+sendToEmail+">", subject, mailBody);
  }
  
  public String sendMail(String strSendFrom, String strSendTo, String strSubject, String mailBody) throws java.rmi.RemoteException {
	  return sendMail(strSendFrom, strSendTo, null, strSubject, mailBody);
  }
  
  public String sendMail(String strSendFrom, String strSendTo, String strCC, String strSubject, String mailBody) throws java.rmi.RemoteException {
	  return sendMail(strSendFrom, strSendTo, strCC, null, strSubject, mailBody);
  }
  
  public String sendMail(String strSendFrom, String strSendTo, String strCC, String strBcc, String strSubject, String mailBody) throws java.rmi.RemoteException {
	  return sendMail(strSendFrom, strSendTo, strCC, strBcc, strSubject, mailBody, null, null, null, null, null, null);
  }
  
  public String sendMail(String strSendFrom, String strSendTo, String strCC, String strBcc, String strSubject, String mailBody, byte[] docbinaryarrayFile1, String strdocName1, byte[] docbinaryarrayFile2, String strdocName2, byte[] docbinaryarrayFile3, String strdocName3) throws java.rmi.RemoteException{
    if (serviceSoap == null)
      _initServiceSoapProxy();
    return serviceSoap.sendMail(strSendFrom, strSendTo, strCC, strBcc, strSubject, mailBody, docbinaryarrayFile1, strdocName1, docbinaryarrayFile2, strdocName2, docbinaryarrayFile3, strdocName3);
  }
  
  public String sendMailwithImage(String strSendFrom, String strSendTo, String strCC, String strBcc, String strSubject, String mailBody, byte[] docbinaryarrayFile1, String strdocName1, byte[] docbinaryarrayFile2, String strdocName2, byte[] docbinaryarrayFile3, String strdocName3) throws java.rmi.RemoteException{
    if (serviceSoap == null)
      _initServiceSoapProxy();
    return serviceSoap.sendMailwithImage(strSendFrom, strSendTo, strCC, strBcc, strSubject, mailBody, docbinaryarrayFile1, strdocName1, docbinaryarrayFile2, strdocName2, docbinaryarrayFile3, strdocName3);
  }
  
  
}