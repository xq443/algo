import sys


class Interval:
    def __init__(self, start, end, brand, bin):
        self.start = start
        self.end = end
        self.brand = brand
        self.bin = bin
        self.if_valid = True
        self.if_covered = False

    def __lt__(self, other):
        if self.start != other.start:
            return self.start < other.start
        return self.end < other.end

    def __repr__(self):
        return self.bin + f"{self.start:010d}" + "," + self.bin + f"{self.end:010d}" + "," + self.brand


intervals = []
low = 0000000000
high = 9999999999

def main():
    bin = sys.stdin.readline().strip()
    count = int(sys.stdin.readline().strip())

    for _ in range(count):
        line = sys.stdin.readline().strip()
        start, end, brand = line.split(',')

        interval = Interval(int(start), int(end), brand, bin)
        intervals.append(interval)

    sorted_intervals = sorted(intervals)
    # part 3
    covered = []
    i = 0
    while i < count - 1:
        cur_interval = sorted_intervals[i]
        next_interval = sorted_intervals[i + 1]
        if cur_interval.start < next_interval.start and next_interval.end < cur_interval.end:
            if cur_interval.brand != next_interval.brand:
                covered.append(next_interval)
                next_interval.if_covered = True
            else:
                next_interval.if_valid = False
        i += 1
    print(covered)
    # part 4
    remove_covered_intervals = []
    for interval in sorted_intervals:
        print(interval.if_covered)
        if interval.if_valid and not interval.if_covered:
            remove_covered_intervals.append(interval)
    print(remove_covered_intervals)
    remove_covered_intervals[0].start = low
    i = 0
    count = len(remove_covered_intervals)
    while i < count - 1:
        cur_interval = remove_covered_intervals[i]
        next_interval = remove_covered_intervals[i+1]
        if cur_interval.brand == next_interval.brand:
            cur_interval.if_valid = False
            next_interval.start = min(cur_interval.start, next_interval.start)
            next_interval.end = max(cur_interval.end, next_interval.end)
        else:
            cur_interval.end = next_interval.start - 1
        i += 1
    remove_covered_intervals[-1].end = high
    # print result
    for interval in remove_covered_intervals:
        if interval.if_valid:
            covered.append(interval)
    for interval in sorted(covered):
        print(interval)


if __name__ == "__main__":
    main()


# 777777
# 2
# 1000000000,3999999999,VISA
# 4000000000,5999999999,MASTERCARD

# 424242
# 1
# 5000000000,6555555555,VISA

# 424242
# 2
# 4000000000,8999999999,MASTERCARD
# 1000000000,3999999999,VISA

# 424242
# 2
# 0000000000,3700000000,VISA
# 6100000000,9999999999,MASTERCARD

# 424242
# 3
# 1000000000,1299999999,VISA
# 1900000000,9399999999,AMEX
# 1500000000,1699999999,MASTERCARD

# 424242
# 4
# 1000000000,5000000000,VISA
# 2000000000,4000000000,CB
# 6000000000,9000000000,CB
# 7000000000, 8000000000,VISA

# 424242
# 2
# 0000000000,5999999999,VISA
# 6000000000,9999999999,VISA

# 424242
# 3
# 1000000000,3999999999,VISA
# 5000000000,6999999999,VISA
# 8000000000,9999999999,MASTERCARD

