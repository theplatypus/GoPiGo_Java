#!/usr/bin/env python 

import gopigo
# arg1 : motor left
# arg2 : motor rigth
# arg3 : steps (18 par tour)
gopigo.enc_tgt(sys.argv[1], sys.argv[2] , sys.argv[3])
