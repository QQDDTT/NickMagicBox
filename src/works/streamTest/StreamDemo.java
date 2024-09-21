package works.streamTest;


import java.lang.reflect.Field;
import java.util.*;

import application.annotation.Argument;

public class StreamDemo {
    private static int fieldLength = 10;
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Argument
    public static void streamTest(String... args){
        Table table = new Table();
        try {
            table.setCells(createCells(10));
        } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
            System.out.println(e.getMessage()); 
        }
        table.foreachCells(c -> {
            System.out.println("1 " + c.getField1());
        }).sortCells(c -> {
            return (int)(c.getField1().charAt(0));
        }).foreachCells(c -> {
            System.out.println("2 " + c.getField1());
        });
    }

    private static LinkedList<Cell> createCells(int max) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException{
        LinkedList<Cell> cells = new LinkedList<Cell>();
        for(int i = 0;i < max;i++){
            Cell c = new Cell();
            for(Field f : Cell.class.getDeclaredFields()){
                f.setAccessible(true);
                if(String.class == f.getType()){
                    int len = (int) (Math.random() * fieldLength) + 1;
                    String value = "";
                    for (int j = 0 ; j < len;j++){
                        byte b = (byte) (Math.random() * (25) + 65);
                        value += (char)b;
                    }
                    f.set(c,value);
                }else{
                    f.set(c,null);
                }
            }
            cells.add(c);
        }
        return cells;
    }
}

class Cell{
    private String field1;
    private String field2;
    private String field3;
    private String field4;
    private String field5;
    private String field6;
    Cell(){}
    void setField1(String field1){
        this.field1 = field1;
    }
    String getField1(){
        return this.field1;
    }
    void setField2(String field2){
        this.field2 = field2;
    }
    String getField2(){
        return this.field2;
    }
    void setField3(String field3){
        this.field3 = field3;
    }
    String getField3(){
        return this.field3;
    }
    void setField4(String field4){
        this.field4 = field4;
    }
    String getField4(){
        return this.field4;
    }
    void setField5(String field5){
        this.field5 = field5;
    }
    String getField5(){
        return this.field5;
    }
    void setField6(String field6){
        this.field6 = field6;
    }
    String getField6(){
        return this.field6;
    }
    public String toString(){
        return "Cell[fieid1:" + this.field1 + ",field2:" + field2 + ",field3:" + field3
            + ",field4:" + field4 + ",field5:" + field5 + ",field6:" + field6 + "]";
    }
}

class Table<C extends Object>{
    private LinkedList<Cell> cells;
    @SuppressWarnings("rawtypes")
    private LinkedList<Table> tables;
    Table(){
        this.cells = new LinkedList<Cell>();
    }
    Table(LinkedList<Cell> cells){
        this.cells = cells;
    }
    void setCells(LinkedList<Cell> cells){
        this.cells = cells;
    }
    @SuppressWarnings("rawtypes")
    Table addCell(Cell cell){
        this.cells.add(cell);
        return this;
    }
    Cell getFirstCell(){
        return this.cells.getFirst();
    }
    LinkedList<Cell> getCells(){
        return this.cells;
    }
    @SuppressWarnings({ "rawtypes" })
    <I extends Number> Table sortCells(ISortCells<I> func){
        this.sorting(func,0,this.cells.size() - 1);
        return this;
    }

    //
    private <I extends Number> void sorting(ISortCells<I> func,int start,int end){
        int low = start;
        int high = end;
        if (low < high){
            while (low < high)
            {
                while (func.sortWith(this.cells.get(low)).doubleValue() <= func.sortWith(this.cells.get(start)).doubleValue() && low < end)
                {
                    low++;//满足小于基准的条件，指针右移
                }
                while (func.sortWith(this.cells.get(high)).doubleValue() >= func.sortWith(this.cells.get(start)).doubleValue() && high > start)
                {
                    high--;//满足大于基准的条件，指针左移
                }
                if (low < high)
                {
                    //交换两个不满足条件的元素
                    Cell c = this.cells.get(low);
                    this.cells.set(low,this.cells.get(high));
                    this.cells.set(high,c);
                }
                else
                {
                    break;
                }
            }
            //插入基准元素
            Cell c = this.cells.get(start);
            this.cells.set(start,this.cells.get(high));
            this.cells.set(high,c);
            sorting(func, start, high - 1);
            sorting(func, high + 1, end);
        }
    }

    @SuppressWarnings("rawtypes")
    <I extends Object> Table groupCells(IGroupCells<I> func){
        this.grouping(func);
        return this;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private <I extends Object> void grouping(IGroupCells<I> func){
        if(this.cells == null){
            if(this.tables != null){
                for(Table t : this.tables){
                    t.grouping(func);
                }
            }
        }else{
            this.tables = new LinkedList<Table>();
            for(Cell c : this.cells){
                if(this.tables.size() == 0){
                    Table t1 = new Table();
                    t1.addCell(c);
                    this.tables.add(t1);
                    continue;
                }
                for(Table t : this.tables){
                    if(t == null){
                        t = new Table();
                        t.addCell(c);
                        break;
                    }
                    else if(func.groupWith(c).equals(func.groupWith(t.getFirstCell()))){
                        t.addCell(c);
                        break;
                    }else{
                        Table t1 = new Table();
                        t1.addCell(c);
                        this.tables.add(t1);
                    }
                }
            }
            this.cells = null;
        }
    }

    @SuppressWarnings("rawtypes")
    Table foreachCells(IForeachCells func){
        this.foreaching(func);
        return this;
    }

    @SuppressWarnings("rawtypes")
    private void foreaching(IForeachCells func){
        if(this.cells == null){
            for(Table t : this.tables){
                t.foreaching(func);
            }
        }else{
            for(Cell c : this.cells){
                func.forCell(c);
            }
        }
    }
}

@FunctionalInterface
interface ISortCells<I extends Number>{
    I sortWith(Cell cell);
}
@FunctionalInterface
interface IGroupCells<I extends Object>{
    I groupWith(Cell cell);
}
@FunctionalInterface
interface IForeachCells{
    void forCell(Cell cell);
}
