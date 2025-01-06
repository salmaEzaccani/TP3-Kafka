package ezaccani.salma.service;


import ezaccani.salma.entities.PageEvent;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.Grouped;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static java.util.Locale.filter;

@Service
public class PageEventService {
    //deployee consumer
    @Bean
    public Consumer<PageEvent> pageEventConsumer(){
         return (input)->{
             System.out.println("********** Page Event *********");
             System.out.println(input.toString());
             System.out.println("********** Page Event *********");
        };
    }


    @Bean
    //deploye un supplier -> pour produir des messgaes
    public Supplier<PageEvent> pageEventSupplier(){
        return ()->new PageEvent(
                //page 1 ou page 2
                Math.random()>0.5?"P1":"P2",
                // User 1 ou User 2
                Math.random()>0.5?"U1":"U2",
                new Date(),
                new Random().nextLong(9000));
    }


    @Bean
    //deploye une fonction qui fait les deux in(pageEvent) & out(pageEvent)
    public Function<PageEvent, PageEvent> pageEventFunction(){
        return (input)->{
            input.setName("L : "+input.getName().length());
            input.setUser("UUUUUUU");
            return input;
        };
    }

    //page P1 il a visite 50 Fois returne -> KTable
    //cle -> String ->(name page)
    //valeur -> Long -> (nbr de visit)
    @Bean
    public Function<KStream<String, PageEvent>, KStream<String, Long>> kStreamFunction() {
        return (input) -> {
          return  input
                .filter((k, v) -> v.getDuration() > 100)
                .map((k, v) -> new KeyValue<>(v.getName(), 0L))
                .groupBy((k, v) -> k, Grouped.with(Serdes.String(), Serdes.Long()))
                .count()
                .toStream();

        };
    }





}
