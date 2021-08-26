"""Social Distancing Tracker

This script allows the user to define a list of a fixed number of people in location of 
pre-determined breadth and width, generate randomised coordinates for those people and
generate a list of those who disobeyed social distancing measures by mingling with more than
8 people within a 2-metre radius.

This script requires that `scipy` be installed within the Python
environment you are running this script in.

This solution leverages on quick neighbour-finding properties of KD Trees. Assuming N people,
the generation of the list of offenders has a runtime of O(N*logN). The brute-force solution has 
an estimated O(N^2) runtime (where for each person in the list, we iterate through the entire list 
and see if they are within 2m of the person in question).
"""

from typing import List, Tuple
import random
import scipy.spatial as spatial


def snapshot(num_people: int = 20_000, hall_length: int = 250, hall_breadth: int = 300) -> List[Tuple[str, float, float]]:
 """ There's 20,000 people in a conference hall of 250m by 300m
 Returns:
 a list of 3-tuple of Person_id, x-coordinate, y-coordinate
 """

 return [(f'Person{i:06}', random.random() * hall_length, random.random() * hall_breadth,) for i in range(num_people)]


def generate_points_list(snapshot_data_list) -> List[Tuple[float, float]]:
    """ Takes in snapshot data list
    Returns:
    a list of 2-tuple of x-coordinate, y-coordinate
    """

    points_list = []
    i = 0
    # For each tuple in snapshot data, extract the x and y-coordinates  
    while i < len(snapshot_data_list): 
        curr_tuple = snapshot_data_list[i]
        curr_x = curr_tuple[1]
        curr_y = curr_tuple[2]
        points_list.append((curr_x, curr_y))
        tmp_list = list()
        i += 1
    
    return points_list

def generate_offenders_list(snapshot_data_list) -> List[Tuple[str, int]]:
    """ Takes in snapshot data list
    Returns:
    a list of 2-tuple of person, number of offenders
    """

    points_list = generate_points_list(snapshot_data_list)
    # Generate k-d tree based on points_list
    point_tree = spatial.cKDTree(points_list)
    offenders_list = []
    i = 0
    while i < len(snapshot_data_list):
        # Creates list of indexes of people within 2m of current person
        neighbours_list = point_tree.query_ball_point(points_list[i], 2)
        # Stores number of neighbours within 2m radius 
        neighbours_count = len(neighbours_list)
        if neighbours_count > 8:
            offenders_list.append((snapshot_data_list[i][0], neighbours_count))
        i += 1

    return offenders_list;    

if __name__ == "__main__":
    data_snapshot = snapshot()
    offender_list = generate_offenders_list(data_snapshot)
    print(offender_list)
