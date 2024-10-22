package org.container;

public class Main {

    public static void main(String[] args) {
        Container container = new Container();
        for (int i = 0; i < 5; i++)
        {
            container.add('a');
            container.add(1);
        }


    }
}