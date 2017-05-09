# Scrabble, anagram generator
## Usage instructions
* Start Main, or open AnagramGenerator.jar (words.txt in same folder)

## Implementation

* Text file gets read by the dictionary and gets inserted in a tree. 
Each string from the 'words.txt' file gets inserted in the tree, this by adding character per character to the tree.
If the character is an end of a word this gets stored in the tree position.
  * Each position contains the character and a boolean stating whether it is an endpoint of a word.
    ![](http://courses.teresco.org/cs211_f09/labs/lexicon/trie3.jpg)

  
* For a given set of characters 
  * All permutations and partial permutations can be found by checking character per character in each level of the tree.
  (e.g. if the set is 'r a e' it will check each letter and check the next permutation. )
  * No duplicate values by using a hashset (overriding the hashmap for the ScrabbleWord is essential for this)
  * All values can be sorted and a list with the highest elements can be generated

* Wildcard characters are allowed

* Existing strings on the board are allowed, and a permutation can be appended to it.

## Examples

Searching by the word perm:

![Imgur](http://i.imgur.com/Ow7oZCQ.png)

Searching with wildcards

![Imgur](http://i.imgur.com/ude2XX1.png)

Finding situation gives an extended string from the word perm:

![Imgur](http://i.imgur.com/z9eNDxP.png)

If selecting highest only only the highest value of the list gets shown:

![Imgur](http://i.imgur.com/jKbFixw.png)
