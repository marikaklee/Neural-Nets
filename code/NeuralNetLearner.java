/**
 *  
 * CS 440/640 Artificial Intelligence
 * Programming Assignment #2
 * 
 * Masaya Ando, Marika Lee, and Jungwoon Shin
 * February 25, 2014
 * 
 * File: NeuralNetLearner.java
 * 
 * Original Skeleton Code by: Zhiaing Ren (Feb 4, 2012)
 * 
 */

package p2;

import java.io.FileNotFoundException;

public class NeuralNetLearner {
	/**
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {
		
		System.out.println("============= Test Neural Net 1 ===============");

		int numTraining1 = 500; 

		int[] layers = { 6, 2, 1 }; // three layers
		NeuralNet net = new NeuralNet(layers);
		net.connectTest();

		double[][] inputvs = { { 1, 1, 0, 0, 0, 0 }, { 1, 0, 1, 0, 0, 0 },
				{ 1, 0, 0, 1, 0, 0 }, { 1, 0, 0, 0, 1, 0 },
				{ 1, 0, 0, 0, 0, 1 }, { 0, 1, 1, 0, 0, 0 },
				{ 0, 1, 0, 1, 0, 0 }, { 0, 1, 0, 0, 1, 0 },
				{ 0, 1, 0, 0, 0, 1 }, { 0, 0, 1, 1, 0, 0 },
				{ 0, 0, 1, 0, 1, 0 }, { 0, 0, 1, 0, 0, 1 },
				{ 0, 0, 0, 1, 1, 0 }, { 0, 0, 0, 1, 0, 1 },
				{ 0, 0, 0, 0, 1, 1 } };

		double[][] outputvs = { { 0 }, { 0 }, { 1 }, { 1 }, { 1 }, 
				{ 0 }, { 1 }, { 1 }, { 1 }, { 1 }, 
				{ 1 }, { 1 }, { 0 }, { 0 }, { 0 } };


		for (int n = 0; n < numTraining1; ++n) {
			net.train(inputvs, outputvs, 1);
		}

		System.out.println(net.errorrate(inputvs, outputvs, 0));




		System.out.println("============= Test Neural Net 2 ===============");

		int numTraining2 = 350;

		int[] layers2 = { 2, 2 }; // two layers
		NeuralNet net2 = new NeuralNet(layers2);
		net2.connectAll();

		double[][] inputvs2  = { { 0, 0 }, { 0, 1 }, { 1, 1 }, { 1, 0 } };
		double[][] outputvs2 = { { 0, 0 }, { 0, 1 }, { 1, 1 }, { 0, 1 } };

		for (int n = 0; n < numTraining2; ++n) {
			net2.train(inputvs2, outputvs2, 1);
		}

		System.out.println(net2.errorrate(inputvs2, outputvs2, 0));




		System.out.println("============= Credit Data Training Data===============");

		int numTrainingCredit = 500;

		DataProcessor dataCredit = new DataProcessor("crx.data.training", 0);
		int[] layers3 = { 15, 30, 1 }; // three layers
		NeuralNet net3 = new NeuralNet(layers3);
		net3.connectAll(1.0);

		double[][] inputvs3 = dataCredit.m_inputvs;
		double[][] outputvs3 = dataCredit.m_outputvs;


		for (int n = 0; n < numTrainingCredit; ++n) {
			net3.train(inputvs3, outputvs3, .25);
			// double error = net3.error(inputvs3, outputvs3);
			// System.out.println("error is " + error);
		}

		System.out.println(net3.errorrate(inputvs3, outputvs3, 0));





		System.out.println("============= Credit Data Testing Data===============");

		dataCredit = new DataProcessor("crx.data.testing", 0);
		net3.connectAll();
		inputvs3 = dataCredit.m_inputvs;
		outputvs3 = dataCredit.m_outputvs;

		System.out.println(net3.errorrate(inputvs3, outputvs3, 0));




		System.out.println("============= Lens Data Training Data===============");

		int numTraining4 = 2000;

		DataProcessor dataLens = new DataProcessor("lenses.training", 1);
		int[] layers4 = { 4, 30, 1 };
		NeuralNet net4 = new NeuralNet(layers4);
		net4.connectAll(1.0);

		double[][] inputvs4 = dataLens.m_inputvs;
		double[][] outputvs4 = dataLens.m_outputvs;

		for (int i = 0; i < numTraining4; i++) {
			net4.train(inputvs4, outputvs4, 1);
		}

		System.out.println(net4.errorrate(inputvs4, outputvs4, 1));

		System.out.println("============= Lens Data Training Data===============");

		dataLens = new DataProcessor("lenses.testing", 1);

		net4.connectAll(0.5);

		inputvs4 = dataLens.m_inputvs;
		outputvs4 = dataLens.m_outputvs;


		System.out.println(net4.errorrate(inputvs4, outputvs4, 1));
		 
/*
		for(int k=2;k<40;k++){
			for(int j=0;j<40;j++){
*/
				int k=7; double j=0.25;

				System.out.println("============= BUBIL Data Training Data===============");


				int numTraining5 = 4000;

				DataProcessor dataBubils = new DataProcessor("BUBIL.training", 2);
				int[] layers5 = { 4, 2,5};
				layers5[1] = k;
				NeuralNet net5 = new NeuralNet(layers5);
				net5.connectAll();

				double[][] inputvs5 = dataBubils.m_inputvs;
				double[][] outputvs5 = dataBubils.m_outputvs;

				for (int i = 0; i < numTraining5; i++) {
					net5.train(inputvs5, outputvs5, j);
				}
				double rate1 = net5.errorrate(inputvs5, outputvs5, 2);
				System.out.println(rate1);
				System.out.println("============= BUBIL Data testing Data===============");
				
				dataBubils = new DataProcessor("BUBIL.testing", 2);

				net5.connectAll();

				inputvs5 = dataBubils.m_inputvs;
				outputvs5 = dataBubils.m_outputvs;

				double rate2 = net5.errorrate(inputvs5, outputvs5, 2);
//				if (rate2 <=0.4)
				System.out.println(rate2);
//				System.out.println("train data set of error rate: " +rate1 +", testing data set error rate: " + rate2 +", k:" + k +", j:" + j);;
				//		System.out.println("rate1: " + rate1 + ", rate2: " + rate2);
/*
			}//inner loop
		} 
*/


		return;
	}

}
