public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n=Integer.parseInt(st.nextToken());
        int m=Integer.parseInt(st.nextToken());
        int arr[][]=new int[n+1][m+1];

        for(int i=1;i<n+1;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<m+1;j++){
                arr[i][j]=Integer.parseInt(st.nextToken())+arr[i-1][j]+arr[i][j-1]-arr[i-1][j-1];
            }
        }
        int max = Integer.MIN_VALUE; // 최대값

        for(int i=1;i<n+1;i++){
            for(int j=1;j<m+1;j++){
                for(int k = i;k<n+1;k++){
                    for(int l=j;l<m+1;l++){
                        max=Math.max(max,arr[k][l]-arr[k][j-1]-arr[i-1][l]+arr[i-1][j-1]);
                    }
                }
            }
        }
        System.out.println(max);
    }
}
