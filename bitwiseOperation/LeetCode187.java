class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> result = new ArrayList<>();
        int len = s.length();
        if (len <= 10) {
            return result;
        }
        // 首先将ACGT和二进制数字00,01,10,11对应起来
        int[] maskMap = new int[26];
        maskMap['A' - 'A'] = 0;
        maskMap['C' - 'A'] = 1;
        maskMap['G' - 'A'] = 2;
        maskMap['T' - 'A'] = 3;
        // 得到一个初始为10的滑动窗口,并映射为hash值
        int hash = 0;
        for (int i = 0; i < 10; i++) {
            hash = (hash << 2) | maskMap[s.charAt(i) - 'A'];
        }
        // 该数组是为了记录所有长度为10的序列压缩存储后的hash值,用索引来存储的,索引处对应的值是该序列出现的次数
        // eg: map[010011] = 1表示CAT序列出现一次
        // eg: map[111001] = 2表示TGC序列出现两次次
        int[] map = new int[1 << 20];
        // 表示初始为10的序列压缩后的hash值出现了一次
        map[hash]++;
        for (int i = 1; i <= len - 10; i++) {
            // 0 9 
            // 1 10
            hash = ((hash << 2) | maskMap[s.charAt(i + 9) - 'A']) & ((1 << 20) - 1);
            map[hash]++;
            if (map[hash] == 2) {
                result.add(s.substring(i, i + 10));
            }
        }
        return result;
    }
}
