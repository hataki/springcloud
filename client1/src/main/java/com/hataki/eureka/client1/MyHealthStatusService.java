package com.hataki.eureka.client1;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Service;

@Service
public class MyHealthStatusService implements HealthIndicator {

    private Boolean status = true ;

    public void setStatus(Boolean status) {
        this.status = status;
    }



    public String getStatus() {
        // TODO Auto-generated method stub
        return this.status.toString();
    }


    @Override
    public Health health() {
        if(status){
            return new Health.Builder().up().build();
        }else{
            return new Health.Builder().down().build();
        }

    }
}
