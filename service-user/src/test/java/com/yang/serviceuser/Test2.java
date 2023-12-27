package com.yang.serviceuser;


import com.alibaba.excel.EasyExcel;
import lombok.Data;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: opay-card->Test
 * @description:
 * @author: LCY
 * @create: 2021-09-18 11:37
 **/
public class Test2 {

  public static void main(String[] args) throws Exception {


    BigDecimal currentAmount=new BigDecimal("717391.05");
    BigDecimal rate= new BigDecimal("0.04645");
    BigDecimal monthAmount= new BigDecimal("3275.76");
    List<Repayment> list=new ArrayList<>(219);
    BigDecimal totalRateAmount=new BigDecimal("0");
    int num=1;
    while (currentAmount.compareTo(new BigDecimal("0"))>0){
      Repayment repayment=new Repayment();
      BigDecimal yearFee = currentAmount.multiply(rate);//年化利息
      BigDecimal divide = yearFee.divide(new BigDecimal(12),3,BigDecimal.ROUND_UP);//月利息
      currentAmount=currentAmount.subtract(monthAmount);//贷款余额
      if (currentAmount.compareTo(new BigDecimal("0"))<=0){
        currentAmount=new BigDecimal("0");
      }
      totalRateAmount=totalRateAmount.add(divide);//累计利息
      repayment.setNum(num);
      repayment.setRepayAmount(monthAmount.add(divide));
      repayment.setRemainAmount(currentAmount);
      repayment.setRateAmount(totalRateAmount);
      list.add(repayment);
      num++;
    }

    try {
      File file=new File("/Users/cherry/Documents/LCY/HK/"+"2023年1月11日 提前还款缩短年限-10w" +".xlsx");
      EasyExcel.write(file, Repayment.class)
              .sheet("sheet0")
              .doWrite(list);
      System.out.println("==============");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Data
  public static class Repayment{
    private int num;

    private BigDecimal repayAmount;

    private BigDecimal remainAmount;

    private BigDecimal rateAmount;
  }

}
