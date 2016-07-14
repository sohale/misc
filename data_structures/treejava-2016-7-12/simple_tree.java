//import JUnit;
//linter


class simple_tree{
    public static void main(String args[]) throws Exception {
        T1Node tree = new T1Node("Hlo");
        String[] dataset = {"1", "22", "333", "4$"};
        T1Node node = tree;
        for(int i=0; i<dataset.length; i++) {
            node.left = new T1Node( dataset[i] );
            //System.out.println(" >"+dataset[i]);
            //System.out.println();
            node = node.left;
        }

        StringBuffer accumulator = new StringBuffer();  // i.e. reducer!
        tree.traverse_preorder(accumulator);
        System.out.println(accumulator);

        StringBuffer v = new StringBuffer();
        tree.visualise1(v);
        System.out.println(v);

        try{
            String lr="";
            System.out.println(lr+" -> "+tree.address(lr));
        }catch(Exception e){
            System.out.println(e);
        }

        StringBuffer accumulator2 = new StringBuffer();  // i.e. reducer!
        tree.traverse_preorder(accumulator2, new Extractor<String, T1Node>(){
            String extract(T1Node entry){
                return entry.data+"";
            };
        });
        System.out.println(accumulator2);

        System.out.println(accumulator2.toString().equals(accumulator.toString()) );

        System.out.println(
            tree.traverse_preorder(new Extractor<String, T1Node>() {
                String extract(T1Node entry){return entry.data;};
            })
        );

        // i.e. reducer
        StringBufferAccumulator accumulator3 = new StringBufferAccumulator();
        tree.traverse_preorder(accumulator3,
            new Extractor<String, T1Node>(){
                String extract(T1Node entry){return entry.data;};
            }
            //todo: Lambda form
            //(Extractor<String, T1Node>)
            //((T1Node entry) -> (entry.data))
        );
        System.out.println(accumulator3.flush());

        System.out.println(tree);
        System.out.println(tree.address(""));
        System.out.println(tree.address("L"));
        System.out.println(tree.address("LL"));
        //System.out.println(tree.address("R"));

        //Repeated:
        System.out.println(tree.visualise1() );

        System.out.println("=");
        for(int i=0;i<6;i++){
            System.out.println(tree.visualise1(i) );
        }

        T1Node n = tree.address("L");
        System.out.println(n);
        System.out.println(n.left);
        n.left = n.left.rotate_right();

        for(int i=0;i<6;i++){
            System.out.println(tree.visualise1(i) );
        }

        /* Rotation decreased the tree height:

            (     -    -    -    -   Hlo)
             (    -    -    -   1  )  -
              (   -    -   22 )  -    -
               (  -   333)  -    -    -
                (4$ )  -    -    -    -
                  -    -    -    -    -

        ->

            (    -    -    -     -   Hlo)
             (   -    -    -    1  )  -
              (  -   333   -  )  -    -
               (4$ )  -  (22 )   -    -
                 -    -    -     -    -
                 -    -    -     -    -
        */

    }
}
