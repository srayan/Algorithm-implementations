import java.io.*;
import java.text.DecimalFormat;

public class project2 
{
    //Initializing 

    static int[] temp = new int[1000];
    static double dReliability = 0;
    static double dLocalProbability = -1;
    static int[][] iLinkMatrix = new int[1024][10];
    static int[][] iRefMatrix = new int[1024][10];
    static int[][] iCompleteMatrix = new int[1024][10];
    static int[] iRandomMatrix = new int[100];
    static int iFlag = 0;

    public static int[][] iLinkCreation(int[][] iConnectivityMatrix) {
        return iMakeCalc(iConnectivityMatrix);
    }

    public static int[][] iMakeCalc(int[][] iPassedMatrix) {
        int ArrCnt = 0;
        for (int a = 0; a <= 1; a++) {
            for (int b = 0; b <= 1; b++) {
                for (int c = 0; c <= 1; c++) {
                    for (int d = 0; d <= 1; d++) {
                        for (int e = 0; e <= 1; e++) {
                            for (int f = 0; f <= 1; f++) {
                                for (int g = 0; g <= 1; g++) {
                                    for (int h = 0; h <= 1; h++) {
                                        for (int i = 0; i <= 1; i++) {
                                            for (int j = 0; j <= 1; j++) {
                                                iPassedMatrix[ArrCnt][0] = a;
                                                iPassedMatrix[ArrCnt][1] = b;
                                                iPassedMatrix[ArrCnt][2] = c;
                                                iPassedMatrix[ArrCnt][3] = d;
                                                iPassedMatrix[ArrCnt][4] = e;
                                                iPassedMatrix[ArrCnt][5] = f;
                                                iPassedMatrix[ArrCnt][6] = g;
                                                iPassedMatrix[ArrCnt][7] = h;
                                                iPassedMatrix[ArrCnt][8] = i;
                                                iPassedMatrix[ArrCnt][9] = j;
                                                ArrCnt++;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return iPassedMatrix;
    }

    public static void main(String[] args) throws IOException {

        int k, i;
        for (i = 0; i < 100; i++) {
            iRandomMatrix[i] = -1;
        }
        dReliability = 0;
        float q = (float) 1.0;
        float p = (float) 0.0;
        if (iFlag == 0) {
            iLinkMatrix = iLinkCreation(iLinkMatrix);
        }
        temp = CalculateVar(iLinkMatrix);
        DecimalFormat df2 = new DecimalFormat("###.##");
        if (iFlag != 0) {
            //Instantiating probability value to fixed value
            p = (float) 0.95;
            q = (float) 0.95;
        }
        for (double probability = p; probability <= q;) {
            for (int j = 0; j < 1000; j++) {
                if (temp[j] != 0) {
                    for (int ik = 0; ik < 10; ik++) {
                        if (iLinkMatrix[temp[j]][ik] == 1) {
                            if (dLocalProbability == -1) {
                                dLocalProbability = probability;
                            } else {
                                dLocalProbability = dLocalProbability * probability;
                            }
                        } else {
                            if (dLocalProbability == -1) {
                                dLocalProbability = (1 - probability);
                            } else {
                                dLocalProbability = dLocalProbability * (1 - probability);
                            }
                        }
                    }
                    dReliability += dLocalProbability;
                    dLocalProbability = -1;
                }
            }
            System.out.println(" " + probability + "\t  " + dReliability);
            //Incrementing value
            probability = probability + 0.01;
            probability = Double.valueOf(df2.format(probability));
            dReliability = 0;
        }
        iRefMatrix = iLinkMatrix;
        iFlag = 1;
        //Running loop for variable k 
        for (k = 1; k <= 100; k++) {
            for (i = 0; i < k; i++) {
                //Based on all 1024 possibilities for 10 edges
                iRandomMatrix[i] = (int) (Math.random() * 1024);
            }
            int j = 0;
            for (i = 0; i < 100; i++);
            for (i = 0; i < 100; i++) {
                if (iRandomMatrix[i] != -1) {
                    for (j = 0; j < 10; j++) {
                        if (iLinkMatrix[iRandomMatrix[i]][j] == 0) {
                            iLinkMatrix[iRandomMatrix[i]][j] = 1;
                        } else {
                            iLinkMatrix[iRandomMatrix[i]][j] = 0;
                        }
                    }
                }
            }
            dReliability = 0;
            q = (float) 1.0;
            p = (float) 0.0;
            if (iFlag == 0) {
                iLinkMatrix = iLinkCreation(iLinkMatrix);
            }
            temp = CalculateVar(iLinkMatrix);
            if (iFlag != 0) {
                p = (float) 0.95;
                q = (float) 0.95;
            }
            for (double probability = p; probability <= q;) {
                for (j = 0; j < 1000; j++) {
                    if (temp[j] != 0) {
                        for (int ki = 0; ki < 10; ki++) {
                            if (iLinkMatrix[temp[j]][ki] == 1) {
                                if (dLocalProbability == -1) {
                                    dLocalProbability = probability;
                                } else {
                                    dLocalProbability = dLocalProbability * probability;
                                }
                            } else {
                                if (dLocalProbability == -1) {
                                    dLocalProbability = (1 - probability);
                                } else {
                                    dLocalProbability = dLocalProbability * (1 - probability);
                                }
                            }
                        }
                        dReliability += dLocalProbability;
                        dLocalProbability = -1;
                    }
                }
                System.out.println(" " + probability + " \t " + dReliability);
                probability = probability + 0.01;
                probability = Double.valueOf(df2.format(probability));
                dReliability = 0;
            }
            iLinkMatrix = iRefMatrix;
            for (i = 0; i < k; i++) {
                iRandomMatrix[i] = -1;
            }
        }
    }

    public static int[] CalculateVar(int[][] iLinkMatrix) throws IOException {
        int[] temp = new int[1024];
        int iCounter = 0;
        for (int i = 0; i < 1024; i++) {
            temp[i] = 0;
        }
        for (int i = 0; i < 1024; i++) {
            int[] checkmatrix = new int[5];
            for (int k = 0; k < 5; k++) {
                checkmatrix[k] = -1;
            }
            if (iLinkMatrix[i][0] == 1) {
                checkmatrix[0] = 0;
                checkmatrix[1] = 1;
            }
            if (iLinkMatrix[i][1] == 1) {
                checkmatrix[0] = 0;
                checkmatrix[2] = 2;
            }
            if (iLinkMatrix[i][2] == 1) {
                checkmatrix[0] = 0;
                checkmatrix[3] = 3;
            }
            if (iLinkMatrix[i][3] == 1) {
                checkmatrix[0] = 0;
                checkmatrix[4] = 4;
            }
            if (iLinkMatrix[i][4] == 1) {
                checkmatrix[1] = 1;
                checkmatrix[2] = 2;
            }
            if (iLinkMatrix[i][5] == 1) {
                checkmatrix[1] = 1;
                checkmatrix[3] = 3;
            }
            if (iLinkMatrix[i][6] == 1) {
                checkmatrix[1] = 1;
                checkmatrix[4] = 4;
            }
            if (iLinkMatrix[i][7] == 1) {
                checkmatrix[2] = 2;
                checkmatrix[3] = 3;
            }
            if (iLinkMatrix[i][8] == 1) {
                checkmatrix[2] = 2;
                checkmatrix[4] = 4;
            }
            if (iLinkMatrix[i][9] == 1) {
                checkmatrix[3] = 3;
                checkmatrix[4] = 4;
            }

            int iCount = 0;

            for (int j = 0; j < 5; j++) {
                iCount += checkmatrix[j];
            }

            if (iCount == 10) {
                temp[iCounter] = i;
                //System.out.print(iCounter+ " ");
                iCounter++;
            }
        }
        return temp;

    }

}
