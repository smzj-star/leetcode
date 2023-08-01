class Solution {
    public String reverseWords(String s) {
        s = s.trim();
        int i = s.length() - 1;
        int j = i;
        StringBuilder sb = new StringBuilder();
        while (i >= 0) {
            // 寻找第一个出现的空格
            while (i >= 0 && s.charAt(i) != ' ') {
                i--;
            }
            sb.append(s, i + 1, j + 1).append(" ");
            while (i >= 0 && s.charAt(i) == ' ') {
                i--;
            }
            j = i;
        }
        return sb.toString().trim();
    }
}
