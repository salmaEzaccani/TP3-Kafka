package ezaccani.salma.web;

import ezaccani.salma.entities.PageEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.binder.kafka.streams.InteractiveQueryService;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Random;

@RestController
public class PageEventRestController {

    @Autowired
    private StreamBridge streamBridge;

    @Autowired
    private InteractiveQueryService interactiveQueryService;


    //publier dans un topic Name
    //utilisee streamBridge
    @GetMapping("/publish/{topic}/{name}")
    public PageEvent publish(@PathVariable String topic, @PathVariable String name){
        PageEvent pageEvent=new PageEvent(name,Math.random()>0.5?"U1":"U2",new Date(),new Random().nextInt(9000));
        //pageEvent.setName(name);
        //pageEvent.setDate(new Date());
        //pageEvent.setDuration(new Random().nextInt(9000));
        //pageEvent.setUser(Math.random()>0.5?"U1":"U2");
        //envoyer le msg
        streamBridge.send(topic,pageEvent);
        return pageEvent;
    }





}
