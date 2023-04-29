public class Container implements Comparable <Container>{
    private String ID;
    private String Short;
    private String Long;
    public String GetID(){
        return ID;
    }
    public void SetID(String id){
        ID=id;
    }
    public String GetShort(){
        return Short;
    }
    public void SetShort(String shortname){
        Short=shortname;
    }
    public String GetLong(){
        return Long;
    }
    public void SetLong(String longname){
        Long=longname ;
    }

    @Override
    public int compareTo(Container C){
        if(this.GetShort().charAt(9)> C.GetShort().charAt(9))
            return 1;
        else if (this.GetShort().charAt(9)< C.GetShort().charAt(9))
            return -1;
        else
            return 0;
    }
    public String toString(){
        return   "    <CONTAINER "+this.GetID()+">\n"
                +"         <SHORT-NAME>"+this.GetShort()+"</SHORT-NAME>\n"
                +"         <LONG-NAME>"+this.GetLong()+"</LONG-NAME>\n"
                +"    </CONTAINER>\n";
    }
}