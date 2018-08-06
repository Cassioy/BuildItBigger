package br.cassioy.javajokelib;

import java.util.ArrayList;
import java.util.Random;

public class JavaJoke {

    public Random randomGenerator = new Random();
    public int index;
    public String jokeString;
    private ArrayList<String> jokesArray = new ArrayList<>();



    public JavaJoke(){
        jokesArray.add("When Chuck Norris was denied a Bacon McMuffin at McDonalds because it was 10:35, he roundhouse kicked the store so hard it became a KFC.");
        jokesArray.add("Some people wear Superman pajamas. Superman wears Chuck Norris pajamas.");
        jokesArray.add("Chuck Norris doesn\'t shower, he only takes blood baths.");
        jokesArray.add("Chuck Norris is not Politically Correct. He is just Correct. Always.");
        jokesArray.add("What\'s Forrest Gump\'s password? \nAnswer: 1Forrest1");
        jokesArray.add("Why can\'t bicycles stand on their own? \nAnswer: They are two tired");
        jokesArray.add("What do you call a singing Laptop? \nAnswer: A Dell");
        jokesArray.add("What\'s the best thing about a Boolean? \nAnswer: Even if you're wrong, you\'re only off by a bit.");
        jokesArray.add("Where do programmers like to hangout? \nAnswer: The Foo Bar");
        jokesArray.add("I just watched a documentary about beavers. It was the best dam show I ever saw");

    }

    public String getJoke() {

        index = randomGenerator.nextInt(jokesArray.size());
        jokeString = jokesArray.get(index);
        return jokeString;
    }
}
