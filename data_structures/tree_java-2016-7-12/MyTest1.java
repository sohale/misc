/*
 -cp .:/usr/share/java/junit4-4.10.jar
-cp .:/usr/share/java/junit4-4.11.jar
*/

import org.junit.Test;
import static org.junit.Assert.*;

public class MyTest1 {

    @Test
    public void testConcatenate() {

        T1Node node = new T1Node("1");
        T1Node tree = node;
        String[] dataset = {"2", "3", "4"};
        for(int i=0; i<dataset.length; i++) {
            node.right = new T1Node( dataset[i] );
            //System.out.println(dataset[i]);
            node = node.right;
        }

        StringBuffer accumulator = new StringBuffer();  // i.e. reducer!
        tree.traverse_preorder(accumulator);

        assertEquals("1234", accumulator.toString());

    }
}
