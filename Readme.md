Http1 + Rest Small Data

```
taleh~$ ab -n 10000 -c 50 "http://localhost:8181/api/1.0/encryption/encrypt?data=asd"
This is ApacheBench, Version 2.3 <$Revision: 1879490 $>
Copyright 1996 Adam Twiss, Zeus Technology Ltd, http://www.zeustech.net/
Licensed to The Apache Software Foundation, http://www.apache.org/

Benchmarking localhost (be patient)
Completed 1000 requests
Completed 2000 requests
Completed 3000 requests
Completed 4000 requests
Completed 5000 requests
Completed 6000 requests
Completed 7000 requests
Completed 8000 requests
Completed 9000 requests
Completed 10000 requests
Finished 10000 requests


Server Software:
Server Hostname:        localhost
Server Port:            8181

Document Path:          /api/1.0/encryption/encrypt?data=asd
Document Length:        28 bytes

Concurrency Level:      50
Time taken for tests:   1.992 seconds
Complete requests:      10000
Failed requests:        0
Total transferred:      1610000 bytes
HTML transferred:       280000 bytes
Requests per second:    5019.39 [#/sec] (mean)
Time per request:       9.961 [ms] (mean)
Time per request:       0.199 [ms] (mean, across all concurrent requests)
Transfer rate:          789.18 [Kbytes/sec] received

Connection Times (ms)
              min  mean[+/-sd] median   max
Connect:        0    3   6.3      2     179
Processing:     1    7  11.3      5     185
Waiting:        1    5   7.3      4     179
Total:          2   10  12.9      8     188

Percentage of the requests served within a certain time (ms)
  50%      8
  66%     10
  75%     11
  80%     12
  90%     14
  95%     17
  98%     20
  99%     23
 100%    188 (longest request)
taleh~$
```

Http1 + Rest Large data (~8KB)

```
Document Length:        8904 bytes

Concurrency Level:      50
Time taken for tests:   2.586 seconds
Complete requests:      10000
Failed requests:        0
Total transferred:      90390000 bytes
HTML transferred:       89040000 bytes
Requests per second:    3866.84 [#/sec] (mean)
Time per request:       12.930 [ms] (mean)
Time per request:       0.259 [ms] (mean, across all concurrent requests)
Transfer rate:          34133.16 [Kbytes/sec] received

Connection Times (ms)
              min  mean[+/-sd] median   max
Connect:        0    3   7.2      2     246
Processing:     1   10  16.1      9     252
Waiting:        0    8  14.3      6     251
Total:          3   13  17.5     11     255

Percentage of the requests served within a certain time (ms)
  50%     11
  66%     12
  75%     13
  80%     14
  90%     16
  95%     18
  98%     22
  99%     31
 100%    255 (longest request)
```

HTTP2 + TLS + Small text

```
taleh~$ h2load "https://localhost:8181/api/1.0/encryption/encrypt?data=a" -t 4 -c 100 -n 10000
starting benchmark...
spawning thread #0: 25 total client(s). 2500 total requests
spawning thread #1: 25 total client(s). 2500 total requests
spawning thread #2: 25 total client(s). 2500 total requests
spawning thread #3: 25 total client(s). 2500 total requests
TLS Protocol: TLSv1.3
Cipher: TLS_AES_256_GCM_SHA384
Server Temp Key: X25519 253 bits
Application protocol: h2
progress: 10% done
progress: 20% done
progress: 30% done
progress: 40% done
progress: 50% done
progress: 60% done
progress: 70% done
progress: 80% done
progress: 90% done
progress: 100% done

finished in 7.52s, 1329.93 req/s, 112.47KB/s
requests: 10000 total, 10000 started, 10000 done, 10000 succeeded, 0 failed, 0 errored, 0 timeout
status codes: 10000 2xx, 0 3xx, 0 4xx, 0 5xx
traffic: 845.70KB (866000) total, 343.65KB (351900) headers (space savings 62.96%), 234.38KB (240000) data
                     min         max         mean         sd        +/- sd
time for request:      671us    663.36ms     69.29ms     74.32ms    94.90%
time for connect:   249.46ms    542.91ms    467.07ms     91.31ms    85.00%
time to 1st byte:   254.89ms    627.89ms    537.32ms    109.40ms    87.00%
req/s           :      13.30       14.07       13.52        0.19    71.00%
taleh~$
```

```
taleh~$ h2load "https://localhost:8181/api/1.0/encryption/encrypt?data=a" -t 4 -c 4 -n 100
starting benchmark...
spawning thread #0: 1 total client(s). 25 total requests
spawning thread #1: 1 total client(s). 25 total requests
spawning thread #2: 1 total client(s). 25 total requests
spawning thread #3: 1 total client(s). 25 total requests
TLS Protocol: TLSv1.3
Cipher: TLS_AES_256_GCM_SHA384
Server Temp Key: X25519 253 bits
Application protocol: h2
progress: 8% done
progress: 16% done
progress: 24% done
progress: 32% done
progress: 40% done
progress: 48% done
progress: 56% done
progress: 64% done
progress: 72% done
progress: 80% done
progress: 88% done
progress: 96% done

finished in 61.40ms, 1628.61 req/s, 140.59KB/s
requests: 100 total, 100 started, 100 done, 100 succeeded, 0 failed, 0 errored, 0 timeout
status codes: 100 2xx, 0 3xx, 0 4xx, 0 5xx
traffic: 8.63KB (8840) total, 3.49KB (3576) headers (space savings 62.36%), 2.34KB (2400) data
                     min         max         mean         sd        +/- sd
time for request:      726us      2.48ms       982us       253us    95.00%
time for connect:    31.82ms     36.89ms     34.13ms      2.58ms    75.00%
time to 1st byte:    34.28ms     38.38ms     36.10ms      2.05ms    75.00%
req/s           :     408.80      434.17      422.73       12.87    75.00%
taleh~$
```

Http2 + TLS + Big Text
```
starting benchmark...
spawning thread #0: 1 total client(s). 25 total requests
spawning thread #1: 1 total client(s). 25 total requests
spawning thread #2: 1 total client(s). 25 total requests
spawning thread #3: 1 total client(s). 25 total requests
TLS Protocol: TLSv1.3
Cipher: TLS_AES_256_GCM_SHA384
Server Temp Key: X25519 253 bits
Application protocol: h2
progress: 8% done
progress: 16% done
progress: 24% done
progress: 32% done
progress: 40% done
progress: 48% done
progress: 56% done
progress: 64% done
progress: 72% done
progress: 80% done
progress: 88% done
progress: 96% done

finished in 122.84ms, 814.04 req/s, 3.86MB/s
requests: 100 total, 100 started, 100 done, 100 succeeded, 0 failed, 0 errored, 0 timeout
status codes: 100 2xx, 0 3xx, 0 4xx, 0 5xx
traffic: 485.39KB (497040) total, 3.69KB (3776) headers (space savings 61.07%), 478.91KB (490400) data
                     min         max         mean         sd        +/- sd
time for request:     1.58ms     21.67ms      3.45ms      3.85ms    96.00%
time for connect:    30.56ms     37.51ms     33.99ms      3.67ms   100.00%
time to 1st byte:    36.79ms     40.73ms     38.81ms      1.84ms    50.00%
req/s           :     203.92      210.36      206.50        3.06    75.00%
```
Http2 + TLS + High bandwith 
```
starting benchmark...
spawning thread #0: 13 total client(s). 2500 total requests
spawning thread #1: 13 total client(s). 2500 total requests
spawning thread #2: 12 total client(s). 2500 total requests
spawning thread #3: 12 total client(s). 2500 total requests
TLS Protocol: TLSv1.3
Cipher: TLS_AES_256_GCM_SHA384
Server Temp Key: X25519 253 bits
Application protocol: h2
progress: 10% done
progress: 20% done
progress: 30% done
progress: 40% done
progress: 50% done
progress: 60% done
progress: 70% done
progress: 80% done
progress: 90% done
progress: 100% done

finished in 4.28s, 2336.27 req/s, 11.07MB/s
requests: 10000 total, 10000 started, 10000 done, 10000 succeeded, 0 failed, 0 errored, 0 timeout
status codes: 10000 2xx, 0 3xx, 0 4xx, 0 5xx
traffic: 47.38MB (49683000) total, 362.26KB (370950) headers (space savings 61.76%), 46.77MB (49040000) data
                     min         max         mean         sd        +/- sd
time for request:     1.36ms    249.52ms     18.80ms     21.04ms    88.16%
time for connect:   164.53ms    400.61ms    296.90ms     97.13ms    62.00%
time to 1st byte:   171.28ms    471.79ms    327.34ms    114.44ms    56.00%
req/s           :      45.19       56.37       49.31        2.44    68.00%
```
