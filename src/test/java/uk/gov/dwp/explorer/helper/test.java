package uk.gov.dwp.explorer.helper;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class test {

    public static void main(String[] args) {
        List<Integer> nums = Arrays.asList(2,1,4);

         nums.stream().map(val -> {
            if (val % 2 == 0) {
                return "e" + val;
            } else {
                return "o" + val;
            }
        }).forEach(val -> System.out.println(val + " "));
    }
}
