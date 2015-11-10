# Neural-Networks
Implemented an algorithm that trains a neural network and practice how to choose various kinds of parameters to achieve good performance of the network. In particular,
            <ul> 
                <li> Implement the "back propogation algorithm" </li>
                <li> Experiment with the structure of the neural network (number of nodes and number of hidden layers)</li>
                <li> Try to find a general rule of thumb in determing the number of nodes in a layer and how many hidden layers are ideal? </li>
                <li> Test how many rounds of training are needed </li>
                <li> Run both mutilclass output data and two output data sets. </li>
            </ul>

Choosing the number of Nodes in a Layer and Setting different learning rates
        <p>
            The range in which optimal number of nodes in a single layer and number of layers in the network varied depending on each data sets. And this was only available by trying out every different data sets.
            <br>
            The parameters and input data are processed from input files from Credit, Lenses and BUBIL data. To find the optimal learning rate, a guess and check method was used for each separate data set. Starting from a predicted learning rate value (ex. learning rate of 1), from there the error rates were compared when different learning rate values were inputted into the algorithm. 
        </p>

Changing the initial weight sizes
        <p>
            It might seem natural to expect that initial weight conditions do not matter because the algorithm is supposed to correct the weight sizes. However, we have found that backpropagation is extremely sensitive to weight sizes as noted by John F. Kolen Jordan B. Pollack (AI Researchers at OSU). At first, our initial sizes were random distribution from 0 to 1. However, changing this to 0 to 0.5 siginificantly improved the error rates for all data sets and layer and layer node sizes. From engineering perspective, this suggests that different weight sizes need to attempted for all different layer sizes and layer node sizes.
            <br>
            Credit Testing data set error rate improved from 0.31 to 0.22.
            Lense Testing data set error rate improved from 0.33 to 0.16.
            BUBIL data set improved from 0.6 to 0.4
        </p>

Back Propagation Algorithm Implementation
        <p>
            The overall purpose of using a back propagation algorithm is so that the we can loop through all the nodes in every layer of the neural network (written in detail below). As we loop through the nodes, we calculate and store a &beta;<sub>j</sub> value for each node, which is the sum of all the weights of every connection multiplied by the outputs of the path. Once the algorithm is in its final phase (last layer of the neural network), we find &beta;<sub>z</sub>, which is the difference between the target and calculated outputs for each node in the last layer. Once &beta;<sub>j</sub> and &beta;<sub>z</sub> (Beta values) are calculated for each node in the network, the algorithm once again loops through every node and re-weights each node based on the Beta values and the passed learning rate. Once that's done, the parameters and input data are processed from an input file of training data for Credit, Lenses and BUBIL data. 
        </p>
        <p>
            In the case of our algorithm, we implemented a function called train() in the NeuralNet.java file, which served a purpose of creating the optimally-weighted neural net for the training data using the back propagation algorithm explained above. Within our code, we determined the error rates to calculate the necessary weight changes for the nodes. So using the back propagation algorithm, with every pass through the data, the weight is automatically adjusted according to the error that was previously calculated. The purpose of this is to minimize the error rates over the course of many passes through the entire training data.
        </p>

Calculating Beta
        <p>
            Here is a more in depth explanation of the weight calculation in the back propagation algorithm mentioned above. As seen in our train() code in NeuralNet.java, as we iterate through the all input nodes in the neural network, we calculate Beta for output nodes, "hidden layer" nodes, and lastly compute the weight changes for all weights in the current layer. 
            First we calculate the beta for the output nodes using delta of target node and the parameter rate. 
            Next we calculate the Beta for the "hidden layer" nodes.
            Lastly, we compute the weight changes for all the weights in the current layer by comparing the prior weights to the current weights of the neural network.


Conclusion
        <p>
            The accuracy of this algorithm depends on many different factors.<br> (1) initial weight conditions <br>(2) input data feature scales<br> (3) quality of each feature in the input data <br>(4) number of layers in the network <br>(5) number of nodes in a layer<br> (6) the nature of each data sets.
         <br><br>
            The idea of changing weight based on the standard whether it will reduce the error rate seems like a very reasonable approach. 
            This algorithm works only on certain ranges of condition. And it is therefore important to use it after all different parameter values are tested using all five different standard above.
        <br><br>
            One very strange obervation point was that no matter what parameters I use(literally tried 1600 different cases of layer size and learning rates), it was never possible to get better than 0.4 for testing data set error rate. Our guess is that data quality might play a role here.
