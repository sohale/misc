class StringBufferAccumulator extends DataAccumulator<String>{
    StringBuffer sb = new StringBuffer();
    void accumulate(String data){
        sb.append(data);
    }
    String get(){
        return sb.toString();
    }
    String flush(){
        String s = this.get();
        sb.setLength(0);
        return s;
    }
}
