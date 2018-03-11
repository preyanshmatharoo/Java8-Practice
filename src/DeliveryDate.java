import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.logging.Logger;
import java.time.DayOfWeek;
import java.time.LocalTime;

/**
 * 
 */

/**
 * @author Preyansh
 *
 */
public class DeliveryDate {

	public static ZoneId pacificZone = ZoneId.of("America/Los_Angeles");
	public static LocalTime cutoffTime = LocalTime.NOON;
	
	public static Logger LOG = Logger.getLogger(DeliveryDate.class.getName());
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String deliveryDays1 = "2";
		String deliveryDays2 = "3";
		
		ZonedDateTime orderDate1 = ZonedDateTime.of(2018, 3, 12, 11 , 4, 35, 9, ZoneId.of("America/Los_Angeles"));
		ZonedDateTime orderDate2 = ZonedDateTime.of(2018, 3, 13, 12 , 4, 35, 9, ZoneId.of("America/Los_Angeles"));
		ZonedDateTime orderDate3 = ZonedDateTime.of(2018, 3, 15, 11 , 4, 35, 9, ZoneId.of("America/Los_Angeles"));
		ZonedDateTime orderDate4 = ZonedDateTime.of(2018, 3, 15, 12 , 4, 35, 9, ZoneId.of("America/Los_Angeles"));
		ZonedDateTime orderDate5 = ZonedDateTime.of(2018, 3, 16, 11 , 4, 35, 9, ZoneId.of("America/Los_Angeles"));
		ZonedDateTime orderDate6 = ZonedDateTime.of(2018, 3, 16, 12 , 4, 35, 9, ZoneId.of("America/Los_Angeles"));
		ZonedDateTime orderDate7 = ZonedDateTime.of(2018, 3, 17, 11 , 4, 35, 9, ZoneId.of("America/Los_Angeles"));
		ZonedDateTime orderDate8 = ZonedDateTime.of(2018, 3, 17, 12 , 4, 35, 9, ZoneId.of("America/Los_Angeles"));
		ZonedDateTime orderDate9 = ZonedDateTime.of(2018, 3, 11, 11 , 4, 35, 9, ZoneId.of("America/Los_Angeles"));
		ZonedDateTime orderDate10 = ZonedDateTime.of(2018, 3, 11, 12 , 4, 35, 9, ZoneId.of("America/Los_Angeles"));
		ZonedDateTime orderDate11 = ZonedDateTime.now();
		
		/*ZonedDateTime indianTime = ZonedDateTime.now();
		ZoneId pacificZone = ZoneId.of("America/Los_Angeles");
		ZonedDateTime pacificTime = ZonedDateTime.now(pacificZone);
		LocalTime ii = LocalTime.now(pacificZone);*/
		
		/*System.out.println("Indian Time : "+indianTime);
		System.out.println("Indian Time Equivalent PST : "+indianTime.withZoneSameInstant(pacificZone));
		System.out.println("Indian Time Equivalent PST : "+indianTime.withZoneSameLocal(pacificZone));
		System.out.println(ii);
		System.out.println(pacificTime);*/

		calculateDeliveryDate(deliveryDays1, orderDate9);
	}

	private static void calculateDeliveryDate(String delDays, ZonedDateTime orderDate){
		
		//DateFormatter 
		ZonedDateTime localOrderDateTime = orderDate;
		ZonedDateTime pstOrderDateTime = orderDate.withZoneSameInstant(pacificZone);
		
		System.out.println("Order Date : " + localOrderDateTime.toLocalDate() + " " + localOrderDateTime.getDayOfWeek());
		System.out.println("Order Time in (" + localOrderDateTime.getZone() + ") : " + orderDate.toLocalTime());
		System.out.println("Order Date in PST: " + pstOrderDateTime.toLocalDate() + " " + pstOrderDateTime.getDayOfWeek());
		System.out.println("Order Time in (" + pstOrderDateTime.getZone() + ") : " + pstOrderDateTime.toLocalTime());
		System.out.println("Cutoff Time in PST : " + cutoffTime);
		
		ZonedDateTime revisedOrderDate = pstOrderDateTime;
		
		if(pstOrderDateTime.toLocalTime().isAfter(cutoffTime)){
			revisedOrderDate = pstOrderDateTime.plusDays(1);
		}
		
		ZonedDateTime finalOrderDate = revisedOrderDate;
		if(DayOfWeek.SATURDAY.equals(revisedOrderDate.getDayOfWeek())){
			finalOrderDate = revisedOrderDate.plusDays(2);
		}else if(DayOfWeek.SUNDAY.equals(revisedOrderDate.getDayOfWeek())){
			finalOrderDate = revisedOrderDate.plusDays(1);
		}
		
		System.out.println("New Order Date : " + finalOrderDate.toLocalDate() + " " + finalOrderDate.getDayOfWeek());
		
		ZonedDateTime newDeliveryDate = finalOrderDate.plusDays(Long.parseLong(delDays));
		
		
		ZonedDateTime finalDeliveryDate = newDeliveryDate;
		if(DayOfWeek.SATURDAY.equals(newDeliveryDate.getDayOfWeek())){
			finalDeliveryDate = newDeliveryDate.plusDays(2);
		}else if(DayOfWeek.SUNDAY.equals(newDeliveryDate.getDayOfWeek())){
			finalDeliveryDate = newDeliveryDate.plusDays(1);
		}
		
		System.out.println("Delivery Date (if non-business day delivery): " + newDeliveryDate.toLocalDate() + " " + newDeliveryDate.getDayOfWeek());
		System.out.println("Delivery Date (if business day delivery): " + finalDeliveryDate.toLocalDate() + " " + finalDeliveryDate.getDayOfWeek());
	}
}
