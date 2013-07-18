package biz.webgate.file;


public class FILEStorageService {

		private static FILEStorageService m_Service;
		
		
		private FILEStorageService() {
			
		}
		
		public static FILEStorageService getInstance() {
			if (m_Service == null) {
				m_Service = new FILEStorageService();
			}
			return m_Service;
		}
}
