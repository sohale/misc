//import java.lang.Exception;
class T1Node {

    String data;
    T1Node left;
    T1Node right;

    //Todo: TreeData abstract class
    T1Node(String data){
        this.data = data;
        this.left = null;
        this.right = null;
    }

// traversing interface
    void traverse_preorder(StringBuffer accumulator) {
        if(this.left!= null) {
            this.left.traverse_preorder(accumulator);
        }
        this.visit(accumulator);
        if(this.right!= null) {
            this.right.traverse_preorder(accumulator);
        }
    }
    void traverse_preorder(StringBuffer accumulator, Extractor<String, T1Node> extractor) {
        if(this.left!= null) {
            this.left.traverse_preorder(accumulator, extractor);
        }

        accumulator.append(extractor.extract(this));

        if(this.right!= null) {
            this.right.traverse_preorder(accumulator, extractor);
        }
    }
    void traverse_preorder(DataAccumulator accumulator, Extractor<String, T1Node> extractor) {
        if(this.left!= null) {
            this.left.traverse_preorder(accumulator, extractor);
        }

        accumulator.accumulate(extractor.extract(this));

        if(this.right!= null) {
            this.right.traverse_preorder(accumulator, extractor);
        }
    }

    StringBuffer traverse_preorder(Extractor<String, T1Node> extractor) {
        StringBuffer accumulator = new StringBuffer();
        traverse_preorder(accumulator, extractor);
        return accumulator;
    }

    void visit(StringBuffer accumulator) {
        //todo: Callback
        // System.out.print(this.data + " ");
        accumulator.append(this.data);
    }
//modification interface
    void insert_either(T1Node new_node, int left_or_right) {
    }

    void insert_left(String data) {
        //this.left.traverse_preorder();
        //this.visit();
        //this.right.traverse_preorder();
    }

/*
    void rotate() {
        // (A(B,-),C) -> (A(B,-),C)
        if(this.left == null) {
            assert this.right != null;
            T1Node a = this;
            T1Node b = this.right;

            T1Node alpha = a; //this;
            T1Node beta = b.left; // this.right.left;
            T1Node gamma = b.right; //this.right.right;

        }else if (this.right == null) {
            assert this.left != null;
            T1Node a = this;
            T1Node b = this.left;

            T1Node alpha = a; //this;
            T1Node beta = b.left; //this.right.left;
            T1Node gamma = b.right; //this.right.right;
        }else{
            assert False
        }
    }
*/

    //Magically keeps the "inorder", but changes the balance.
    T1Node rotate_left() {
        assert this.right != null;
        // assert this.right == null;
        System.out.println("this.right: " + this.right);
        T1Node a = this;
        T1Node b = this.right;

        T1Node alpha = a.left;
        T1Node beta = b.left;
        T1Node gamma = b.right;

        //T1Node temp = a.left;
        //a.left = alpha; // no change
        a.right = beta;
        b.left = a;
        b.right = gamma;

        T1Node new_root = b;
        return new_root;
    }

    T1Node rotate_right() {
        assert this.left != null;
        T1Node a = this.left;
        T1Node b = this;

        T1Node alpha = a.left;
        T1Node beta = a.right;
        T1Node gamma = b.right;

        //a.left = alpha;  //keeps it?
        a.right = b; //beta;
        b.left = beta;
        b.right = gamma;

        T1Node new_root = a;
        return new_root;
        //proglang: move, reversible, noclone.
    }
//fancy addressing and visualising
    //Vector<String>
    void visualise1(StringBuffer accumulator) {
        accumulator.append("(");
        if(this.left!= null) {
            this.left.visualise1(accumulator);
            accumulator.append(" ");
        }
        //this.visit(accumulator);
        accumulator.append(this.data);
        if(this.right!= null) {
            accumulator.append(" ");
            this.right.visualise1(accumulator);
        }
        accumulator.append(")");
    }
    /*
    void visualise1(StringBuffer accumulator) {
        this.visualise1(accumulator, -1 );
    }
    */
    void visualise1(StringBuffer accumulator, int level) {
        final int width = 3;
        /* Example output:
            (    -    -    -     -   Hlo)
             (   -    -    -    1  )  -
              (  -   333   -  )  -    -
               (4$ )  -  (22 )   -    -
                 -    -    -     -    -
                 -    -    -     -    -
        */
        boolean produce_output = (level==0);
        if(produce_output)
            accumulator.append("(");
        else
            accumulator.append(" ");
        if(this.left!= null) {
            this.left.visualise1(accumulator, level-1);
            //if(produce_output)
                accumulator.append(" ");
        }
        //this.visit(accumulator);
        if(produce_output)
            accumulator.append((this.data+"     ").substring(0, width));
        else
            accumulator.append(" -     ".substring(0, width));

        if(this.right!= null) {
            //if(produce_output)
                accumulator.append(" ");
            this.right.visualise1(accumulator, level-1);
        }
        if(produce_output)
            accumulator.append(")");
        else
            accumulator.append(" ");
    }
    StringBuffer visualise1(int level) {
        // this is root
        StringBuffer accumulator = new StringBuffer();
        this.visualise1(accumulator, level);
        return accumulator;
    }
    StringBuffer visualise1() {
        // this is root
        return this.visualise1(-1);
    }

    T1Node address(String lr_address) throws Exception {
        if(lr_address.equals("")) {
            //Will never return null. Just sends an exception
            return this; //.data;
        }
        //System.out.println(lr_address.length());
        switch(lr_address.charAt(0)) {
            case 'L':
                 //if(!this.left) throw ("Node not found at: ");
                return this.left.address(lr_address.substring(1));
                //proglang: local-return. Strictly D.R.Y.
            case 'R':
                 //if(!this.right) throw ("Node not found at: ");
                return this.right.address(lr_address.substring(1));
            default:
                throw new Exception("Bad address");
        }
    }
}
