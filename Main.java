import java.util.Scanner;
 class Main {
    public static void result(int block_of_allocation[], int process_t[], int block_t[]){
        System.out.println("\tallocation_number\t\tProcess size\t\tBlock number");
        for(int i=0; i<process_t.length;i++){
            if(block_of_allocation[i]!=-1){
                System.out.println("\t"+(i+1)+"\t\t\t\t"+process_t[i]+"\t\t\t"+block_of_allocation[i]+"\n");
            }
            else{
                System.out.println("\t"+(i+1)+"\t\t\t\t"+process_t[i]+"\t\t\tNot allocated\n");
            }
        }
    }

    public static void first_fit(int block_t[], int process_t[]){
        int[] block_of_allocation = new int[process_t.length];
        for(int i=0;i<process_t.length;i++){
            block_of_allocation[i]= -1;
        }
        for(int i=0;i<process_t.length;i++){
            for(int j=0; j<block_t.length; j++){
                boolean allocated=false;
                if(block_t[j] >= process_t[i]){
                    System.out.println("process size "+ process_t[i]+ "is allocated in block: " +(j+1)+"\n");
                    block_t[j] -= process_t[i];
                    allocated= true;
                    block_of_allocation[i]= j+1;
                    break;
                }
                else if(allocated == false){
                    System.out.println("there is no space in the memory, process: "+process_t[i]+" not allocated in block: "+j+1+"\n");
                }
            }
        }
        result(block_of_allocation, process_t, block_t);
    }
/*
    public static void Last_fit(int block_t[], int process_t[]){
        int[] block_of_allocation = new int[p];
        for(int i=0;i<process_t.length;i++){
            block_of_allocation[i]= -1;
        }
        for(int i=0;i<process_t.length;i++){
            for(int j=block_t.length-1;j>=0; j--){
                boolean allocated=false;
                if(block_t[j] >= process_t[i]){
                    System.out.println("process size "+ process_t[i]+ "is allocated in block: " +block_t[j]+"\n");
                    block_t[j] -= process_t[i];
                    allocated= true;
                    block_of_allocation[i]= j+1;
                    break;
                }
                else if(allocated == false){
                    System.out.println("there is no space in the memory, process: "+process_t[i]+" not allocated in block: "+block_t[j]+"\n");
                }
            }
        }
        result(block_of_allocation, process_t, block_t);
    }

*/
    public static void Best_fit(int block_t[], int process_t[]){

        int[] block_of_allocation = new int[process_t.length];
        for(int i=0;i<process_t.length;i++){
            block_of_allocation[i]= -1;
        }
        for(int i=0; i<process_t.length;i++){
            int bestidx=-1;
            for (int j = 0; j < block_t.length; j++) {

                if (block_t[j] >= process_t[i]) {
                    if(bestidx==-1){
                        bestidx = j;
                    }
                    else if(block_t[bestidx]>=block_t[j]){
                        bestidx = j;
                    }
                }
            }
            if (bestidx!=-1) {
                System.out.println("process size "+ process_t[i]+ "is allocated in block: " +block_t[bestidx]+"\n");
                block_of_allocation[i]= bestidx+1;
                block_t[bestidx] -= process_t[i];
            }
        }
        result(block_of_allocation, process_t, block_t);
    }

    public static void Worst_fit(int block_t[], int process_t[]){

        int[] block_of_allocation = new int[process_t.length];
        for(int i=0;i<process_t.length;i++){
            block_of_allocation[i]= -1;
        }
        for(int i=0; i<process_t.length;i++){
            int worstidx=-1;
            for (int j = 0; j < block_t.length; j++) {

                if (block_t[j] >= process_t[i]) {
                    if(worstidx==-1){
                        worstidx = j;
                    }
                    else if(block_t[worstidx]<=block_t[j]){
                        worstidx = j;
                    }
                }
            }
            if (worstidx!=-1) {
                System.out.println("process size "+ process_t[i]+ "is allocated in block: " +block_t[worstidx]+"\n");
                block_of_allocation[i]= worstidx+1;
                block_t[worstidx] -= process_t[i];
            }
        }
        result(block_of_allocation, process_t, block_t);
    }

    public static void Next_fit(int block_t[], int process_t[]) {
        int[] block_of_allocation = new int[process_t.length];
        for(int i=0;i<process_t.length;i++){
            block_of_allocation[i]= -1;
        }
        int j = 0;
        for(int i = 0; i < process_t.length; i++){
            while(j<block_t.length){
                if(block_t[j]>=process_t[i]){
                    block_of_allocation[i]=j+1;
                    block_t[j]-=process_t[i];
                    break;
                }
                j=(j+1)%block_t.length;
            }
            result(block_of_allocation, process_t, block_t);
        }
    }

    public static void main(String[] args){
        int b, p;
        System.out.println("insert the number of elements in blocks table: ");
        Scanner input = new Scanner(System.in);
        b= input.nextInt();
        int[] block_t = new int[b];
        for(int i=0;i<b;i++){
            System.out.println("insert the element number"+(i+1));
            int element= input.nextInt();
            block_t[i] = element;
        }
        System.out.println("\ninsert the number of elements in Process table: ");
        p=input.nextInt();
        int[] process_t = new int[p];
        for(int i=0;i<p;i++){
            System.out.println("insert the element number"+(i+1));
            int element = input.nextInt();
            process_t[i] = element;
        }
        int sw = input.nextInt();
        input.close();
        switch (sw) {
            case 1:
                System.out.println("FIRST_FIT\n");
                first_fit(block_t, process_t);
                break;
            case 2:
                System.out.println("BEST_FIT\n");
                Best_fit(block_t, process_t);
                break;
            case 3:
                System.out.println("WORST_FIT\n");
                Worst_fit(block_t, process_t);
                break;
            case 4:
                System.out.println("NEXT_FIT\n");
                Next_fit(block_t, process_t);
                break;

            /*case 22:
               / System.out.println("LAST_FIT");
                Last_fit(block_t, process_t);
                */
            default:
            break;
        }
    }
}
