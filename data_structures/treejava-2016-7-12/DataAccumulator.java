abstract class DataAccumulator<Data>{
    abstract void accumulate(Data data);
    abstract Data get();
    abstract Data flush();
}
