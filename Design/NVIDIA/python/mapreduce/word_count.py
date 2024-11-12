# from mrjob.job import MRJob

# class MRWordFrequencyCount(MRJob):

#     def mapper(self, _, line):
#         # Split the line into words
#         for word in line.split():
#             # Yield each word as a key with a count of 1
#             yield (word.lower(), 1)

#     def reducer(self, word, counts):
#         # Sum all the counts for each word
#         yield (word, sum(counts))

# if __name__ == '__main__':
#     MRWordFrequencyCount.run()


import multiprocessing
from collections import defaultdict

def mapper(line):
    word_counts = defaultdict(int)
    for word in line.split():
        word_counts[word.lower()] += 1
    return word_counts

def reducer(counts1, counts2):
    for word, count in counts2.items():
        counts1[word] += count
    return counts1

def map_reduce(lines):
    with multiprocessing.Pool() as pool:
        # Map step
        mapped = pool.map(mapper, lines)
        
        # Reduce step
        total_counts = defaultdict(int)
        for counts in mapped:
            total_counts = reducer(total_counts, counts)
    
    return total_counts

if __name__ == '__main__':
    with open('input.txt', 'r') as f:
        lines = f.readlines()
    
    word_counts = map_reduce(lines)
    
    for word, count in word_counts.items():
        print(f'{word}: {count}')

