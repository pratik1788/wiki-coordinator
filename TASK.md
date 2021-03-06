**NOTE: Candidates should receive a copy of the latest version of this file in the main branch.**


Data Engineer Challenge
===
Our data engineers are responsible for the data extraction, integration, storage and retrieval of every artist-fan interaction in the world. Building and optimizing these workflows poses many unique challenges. We’re looking for someone who gets excited about making this better. We strive to build simple, resilient solutions that will scale with more engineers, scientists, users, and data.

As a test for these abilities, we're asking you to build a pipeline to store and analyze a small dataset chosen from Wikipedia's publicly available server logs (http://dumps.wikimedia.org/other/pagecounts-raw). Why Wikipedia? It is one of the data sources we actually use!


Instructions
---
Find the 10 most popular wikipedia pages, by language, during the first hour of 2012.

We're looking for a solution that will run all necessary assets on a network of arbitrary size and a simple programming implementation that will perform the required computations on the data set.


Requirements
---
1. Select some combination of database/processing platforms. We want the decisions here to be entirely up to you. The most important aspect here is that you can explain the thought process behind your decisions. Hint: Consider what distributed technologies would make it theoretically possible to scale this assignment to many hours of page views in the future.

2. Create and run an importation command or procedure for loading the wikipedia data set into some database for later analysis. Bonus points if you design a workflow that can be run not just for the aforementioned hour of page views, but for any arbitrary hour.

3. Develop a small application in your favorite language to read from your data store and calculate the 10 most popular pages by language. Bonus points for coming up with other useful or interesting queries/applications of the dataset.

4. Your submission should contain a README that contains step-by-step instructions on how to set up and run your code as well as any insight into your thought process that you'd like to share.  These are both very important!

5. Even though the challenge is scoped for a smaller dataset, it's preferrable to see tech choices that can scale out horizontally if the pageviews dataset were to theoretically become too large for a single machine to analyze.

6. Please try to design the system so that we can run it on our workstations. This might sound in conflict with designing for parallel scalability, but there are ways to achieve both. Many good open-source solutions for distributed processing support a local runner mode.

7. Please include automated unit tests and instructions on how to run them.

8. Your code should be able to output the results to one or more human readable data files (I.E CSV, TSV, JSON, etc.)


Input
---
The input file for this project can be found at http://dumps.wikimedia.org/other/pagecounts-raw/2012/2012-01/pagecounts-20120101-000000.gz (65 MB compressed). This file contains lines like this:
```
language page_name non_unique_views bytes_transferred
en George_Clinton_(royal_governor) 1 22439
en George_Clinton_(vice_president) 9 198844
en George_Clinton_and_His_Gangsters_of_Love 1 14603
en George_Clymer 4 86543
en George_Cockerill 1 6950
en George_Codinus 1 377
```

We're only interested in the language, page, and view counts and any page names prefixed by `Special:`, `User:`, `File:`, etc. can be ignored. For example, lines like this should be excluded:
```
aa.b Special:WhatLinksHere/MediaWiki:Timezoneoffset 1 5449
aa Image:FMA-Voltaire.jpg 1 720
aa User:ReyBrujo/Dumps/20070425 1 6997
```

Helpul tips
---

1. Each evaluator will spend 30 min to 1 hour at an individual submission. Clarity of directions and ease of setup are paramount. If you have a lot of complicated setup steps this can work against your submission.

2. Your simplicity of design is a big factor. A submission that relies on 1 or 2 components is preferrable to one with many complex databases and moving pieces.

3. We want to see what you can do with technologies with which you are already familiar.  Don't choose technologies that require you to stretch too far and make mistakes.



Conclusion
---
More than anything it's important to remember this is a creative exercise that you hopefully find fun on some level. There are no right or wrong answers. The difference between a good submission and a great submission is very often in the details and how much effort we can tell was put in. We're looking for signs that you enjoy working on these types of problems. Step-by-step running directions really help and are encouraged. Clarity in explaining your different decisions and steps is probably the most important aspect. If any of the requirements above seem limiting or you know of a better way to do something, let us know!
