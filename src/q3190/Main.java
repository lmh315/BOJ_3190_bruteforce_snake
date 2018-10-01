package q3190;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int N, K, L, s_dir = 0, e_dir = 0, len_snake = 1;
	static int[] start_snake = { 0, 0 }, end_snake = { 0, 0 };
	static int[] dR = { 0, 1, 0, -1 };
	static int[] dC = { 1, 0, -1, 0 };
	static int[][] map;
	static int[] hm = new int[10001];
	static boolean[] visited = new boolean[10001];

	public static void main(String[] args) throws IOException {
		int i, s_r, s_c, e_r, e_c;
		int cnt = 0;

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		N = Integer.parseInt(in.readLine());
		K = Integer.parseInt(in.readLine());
		map = new int[N][N];

		for (i = 0; i < K; i++) {
			st = new StringTokenizer(in.readLine());
			map[Integer.parseInt(st.nextToken())-1][Integer.parseInt(st.nextToken())-1] = 2;
		}

		L = Integer.parseInt(in.readLine());

		for (i = 0; i < L; i++) {
			st = new StringTokenizer(in.readLine());
			int t = Integer.parseInt(st.nextToken());
			char ch = st.nextToken().charAt(0);
			if (ch == 'D')
				hm[t] = 1;
			else if (ch == 'L')
				hm[t] = 2;
		}

		map[0][0] = 1;

		while (true) {
			cnt++;

			s_r = start_snake[0] + dR[s_dir];
			s_c = start_snake[1] + dC[s_dir];
			e_r = end_snake[0] + dR[e_dir];
			e_c = end_snake[1] + dC[e_dir];

			// after move snake
			if (s_r < 0 || s_r >= N || s_c < 0 || s_c >= N)
				break;
			if (map[s_r][s_c] == 1)
				break;
			// eat apple

			if (map[s_r][s_c] == 2) {
				len_snake++;

				map[s_r][s_c] = 1;

				start_snake[0] = s_r;
				start_snake[1] = s_c;
			} else if (map[s_r][s_c] == 0) {
				map[s_r][s_c] = 1;
				map[end_snake[0]][end_snake[1]] = 0;

				start_snake[0] = s_r;
				start_snake[1] = s_c;
				end_snake[0] = e_r;
				end_snake[1] = e_c;
			}

			if (hm[cnt] != 0) {
				if (hm[cnt] == 1)
					s_dir = (s_dir + 1) % 4;
				else if (hm[cnt] == 2)
					s_dir = (s_dir + 3) % 4;
			}

			if (cnt - len_snake + 1 > 0 && hm[cnt - len_snake + 1] != 0 && !visited[cnt-len_snake + 1]) {
				visited[cnt-len_snake + 1] = true;
				if (hm[cnt - len_snake + 1] == 1)
					e_dir = (e_dir + 1) % 4;
				else if (hm[cnt - len_snake + 1] == 2)
					e_dir = (e_dir + 3) % 4;
			}
		}

		out.write(String.valueOf(cnt));
		out.flush();

		in.close();
		out.close();
	}
}
