import numpy as np
import matplotlib.pyplot as plt

t_size = 396160

b_5000 = np.loadtxt(fname='../base5000.txt', delimiter=',')
b_10000 = np.loadtxt(fname='../base10000.txt', delimiter=',')
b_20000 = np.loadtxt(fname='../base20000.txt', delimiter=',')
b_30000 = np.loadtxt(fname='../base30000.txt', delimiter=',')
b_40000 = np.loadtxt(fname='../base40000.txt', delimiter=',')
i_5000 = np.loadtxt(fname='../impr5000.txt', delimiter=',')
i_10000 = np.loadtxt(fname='../impr10000.txt', delimiter=',')
i_20000 = np.loadtxt(fname='../impr20000.txt', delimiter=',')
i_30000 = np.loadtxt(fname='../impr30000.txt', delimiter=',')
i_40000 = np.loadtxt(fname='../impr40000.txt', delimiter=',')


def compute_stats(data):
    minimum = np.amin(data, axis=0)
    maximum = np.amax(data, axis=0)
    median = np.median(data, axis=0)
    first_q = np.percentile(data, 25, axis=0)
    third_q = np.percentile(data, 75, axis=0)
    return minimum, maximum, median, first_q, third_q

def plot(x, data, name):
    y = compute_stats(data)
    plt.plot(x, y[0], 'b-', label='Min')
    plt.plot(x, y[1], 'g-', label='Max')
    plt.plot(x, y[2], 'r-', label='Median')
    plt.plot(x, y[3], 'm-', label='First Quartile')
    plt.plot(x, y[4], 'c-', label='Thrid Quartile')
    plt.xlabel('Time')
    plt.ylabel('Number of triangles Estimate')
    plt.legend(loc='best')
    plt.title(name)
    plt.show()

x = np.arange(1, t_size+1)

plot(x, i_5000, "Triest-impr with sample size of 5000")
plot(x, i_10000, "Triest-impr with sample size of 10000")
plot(x, i_20000, "Triest-impr with sample size of 20000")
plot(x, i_30000, "Triest-impr with sample size of 30000")
plot(x, i_40000, "Triest-impr with sample size of 40000")
plot(x, b_5000, "Triest-base with sample size of 5000")
plot(x, b_10000, "Triest-base with sample size of 10000")
plot(x, b_20000, "Triest-base with sample size of 20000")
plot(x, b_30000, "Triest-base with sample size of 30000")
plot(x, b_40000, "Triest-base with sample size of 40000")

