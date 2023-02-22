import java.io.*;
import hex.genmodel.easy.RowData;
import hex.genmodel.easy.EasyPredictModelWrapper;
import hex.genmodel.easy.prediction.*;
import hex.genmodel.MojoModel;

public class main {
  public static void main(String[] args) throws Exception {
    EasyPredictModelWrapper.Config config = new EasyPredictModelWrapper.Config()
        .setModel(MojoModel.load("GBM_grid_1_AutoML_1_20230217_161120_model_266.zip"))
        .setEnableLeafAssignment(true)
        .setEnableContributions(true);
    EasyPredictModelWrapper model = new EasyPredictModelWrapper(config);

    RowData row = new RowData();
    row.put("age", "59");
    row.put("sex", "male");
    row.put("bmi", "27.6");
    row.put("children", "0");
    row.put("smoker", "no");
    row.put("region", "southwest");

    RegressionModelPrediction p = model.predictRegression(row);

    System.out.println("Predicted cost: ");
    System.out.println(p.value);
    System.out.println("");

    System.out.println("Shapley contributions: ");
    for (int i=0; i < p.contributions.length; i++) {
      if (i > 0) {
        System.out.print(", ");
      }
      System.out.print(model.getContributionNames()[i] + ": " + p.contributions[i]);
    }
    System.out.println("");

  }
}