package com.raghu.ride.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.raghu.ride.models.RideFare;
import com.raghu.ride.models.VehicleType;
import com.raghu.ride.models.RideDTO;
import com.raghu.ride.models.RideDetailsDTO;

import java.util.ArrayList;

@Service
public class OlaService  {

    private RideFare olaCab;
    private RideFare olaBike;
    private RideFare olaAuto;

    @Autowired
    public OlaService(@Qualifier("olaCab") RideFare olaCab, @Qualifier("olaBike") RideFare olaBike, @Qualifier("olaAuto") RideFare olaAuto) {
        this.olaCab = olaCab;
        this.olaBike = olaBike;
        this.olaAuto = olaAuto;

    }

    public RideDTO getRideDetails(double distance,double time) {
        RideDTO rideDTO = new RideDTO();
        rideDTO.setProvider("Ola");
        ArrayList<RideDetailsDTO> rides = new ArrayList<>();
        rides.add(new RideDetailsDTO(VehicleType.CAB, olaCab.calculateFare(distance), olaCab.calculateETA(time)));
        rides.add(new RideDetailsDTO(VehicleType.BIKE, olaBike.calculateFare(distance), olaBike.calculateETA(time)));
        rides.add(new RideDetailsDTO(VehicleType.AUTO, olaAuto.calculateFare(distance), olaAuto.calculateETA(time)));
        rideDTO.setRides(rides);
        return rideDTO;
    }

        

        // driver.get("https://book.olacabs.com/?lat=12.98059975798607&lng=77.7473784982007&pickup_name=Whitefield&drop_lat=12.927923&drop_lng=77.627108&drop_name=Koramangala&category=compact");
        // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("ola-app")));
        // JavascriptExecutor js = (JavascriptExecutor) driver;
        // // WebElement locationPermision = (WebElement) js.executeScript(
        // //     "return document.querySelector('ola-app').shadowRoot.childNodes[12].childNodes[1].shadowRoot.childNodes[7].shadowRoot.childNodes[7].childNodes[1].childNodes[9]"
        // // );
        // // if(locationPermision != null) {
        // //    locationPermision.click();
        //     // sleep(2);
        // sleep(2);
        // WebElement notificationPermision = (WebElement) js.executeScript(
        //     "return document.querySelector('ola-app').shadowRoot.childNodes[4].shadowRoot.childNodes[7].childNodes[1].childNodes[9]"
        // );
        // notificationPermision.click();
        // sleep(2);
        // WebElement loginBtn = (WebElement) js.executeScript(
        //     "return document.querySelector('ola-app').shadowRoot.childNodes[6].childNodes[1].childNodes[12]"
        // );
        // if(loginBtn != null){
        //     loginBtn.click();
        //     sleep(2);
        //     By loginPhoneNumberSelector = By.cssSelector("#phone-number");
        //     driver.findElement(loginPhoneNumberSelector).sendKeys("8106412944");
        //     sleep(1);
        //     // WebElement loginContinueBtn = (WebElement) js.executeScript(
        //     //     "document.body.childNodes[1].childNodes[0].childNodes[2].childNodes[0].childNodes[6]"
        //     // );
        //     // loginContinueBtn.click();
        //     // sleep(2);

        // }

}
