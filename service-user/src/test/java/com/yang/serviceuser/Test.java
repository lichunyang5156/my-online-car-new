package com.yang.serviceuser;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import lombok.Data;

import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: opay-card->Test
 * @description: 提前还房贷计算器
 * @author: LCY
 * @create: 2021-09-18 11:37
 **/
public class Test {

  private static final BigDecimal rate= new BigDecimal("0.04645");

  public static void main(String[] args) throws Exception {

    String downloadUrl="/Users/cherry/Documents/LCY/HK/提前还款计划/20w.xlsx";
    BigDecimal totalAmount=new BigDecimal("1186666.8");
    int month= 222;
    BigDecimal monthAmount= new BigDecimal("5333.33");
    List<Repayment> currentPlanList = computeCurrentPlan(monthAmount, totalAmount, month);//当前还款计划
    BigDecimal reduceMonthAmount= new BigDecimal("4434.46");
    totalAmount=totalAmount.subtract(new BigDecimal("200000"));
    List<Repayment> reduceMonthRepaymentsList = computeCurrentPlan(reduceMonthAmount, totalAmount, month);//减少月供

    List<Repayment> reduceMonthAmountRepaymentsList = computeReduceMonthAmount(totalAmount);
    ExcelWriter excelWriter=null;
    try {
//      EasyExcelUtil.moreWrite(map,downloadUrl,null);
      FileOutputStream fot=new FileOutputStream(downloadUrl);
      excelWriter= EasyExcel.write(fot).build();

      WriteSheet sheet0 = EasyExcel.writerSheet(0, "当前还款计划").head(Repayment.class).build();
      WriteSheet sheet1 = EasyExcel.writerSheet(1, "减少月供还款计划").head(Repayment.class).build();
      WriteSheet sheet2 = EasyExcel.writerSheet(2, "减少期限还款计划").head(Repayment.class).build();
      excelWriter.write(currentPlanList,sheet0);
      excelWriter.write(reduceMonthRepaymentsList,sheet1);
      excelWriter.write(reduceMonthAmountRepaymentsList,sheet2);

      System.out.println("==============");
    } catch (Exception e) {
      e.printStackTrace();
    }finally {
      excelWriter.finish();
    }
  }

  @Data
  public static class Repayment{
    private int num;

    private BigDecimal repayAmount;

    private BigDecimal remainAmount;

    private BigDecimal rateAmount;
  }

  private static List<Repayment> computeCurrentPlan(BigDecimal monthAmount,BigDecimal totalAmount,int month){
    BigDecimal currentAmount=totalAmount;
    List<Repayment> list=new ArrayList<>(222);
    BigDecimal totalRateAmount=new BigDecimal("0");
    for (int i=1;i<=month;i++){
      Repayment repayment=new Repayment();

      BigDecimal yearFee = currentAmount.multiply(rate);//年化利息
      BigDecimal divide = yearFee.divide(new BigDecimal(12),3,BigDecimal.ROUND_UP);//月利息
      currentAmount=currentAmount.subtract(monthAmount);//贷款余额
      totalRateAmount=totalRateAmount.add(divide);//累计利息
      repayment.setNum(i);
      repayment.setRepayAmount(monthAmount.add(divide));
      repayment.setRemainAmount(currentAmount);
      repayment.setRateAmount(totalRateAmount);
      list.add(repayment);
    }

    return list;
  }

  private static List<Repayment> computeReduceMonthAmount(BigDecimal totalAmount) {
    BigDecimal currentAmount = totalAmount;
    BigDecimal rate = new BigDecimal("0.04645");
    BigDecimal monthAmount = new BigDecimal("5333.33");
    List<Repayment> list = new ArrayList<>(222);
    BigDecimal totalRateAmount = new BigDecimal("0");
    int num = 1;
    while (currentAmount.compareTo(new BigDecimal("0")) > 0) {
      Repayment repayment = new Repayment();
      BigDecimal yearFee = currentAmount.multiply(rate);//年化利息
      BigDecimal divide = yearFee.divide(new BigDecimal(12), 3, BigDecimal.ROUND_UP);//月利息
      currentAmount = currentAmount.subtract(monthAmount);//贷款余额
      if (currentAmount.compareTo(new BigDecimal("0")) <= 0) {
        currentAmount = new BigDecimal("0");
      }
      totalRateAmount = totalRateAmount.add(divide);//累计利息
      repayment.setNum(num);
      repayment.setRepayAmount(monthAmount.add(divide));
      repayment.setRemainAmount(currentAmount);
      repayment.setRateAmount(totalRateAmount);
      list.add(repayment);
      num++;
    }
    return list;
  }
}
