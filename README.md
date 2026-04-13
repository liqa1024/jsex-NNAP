# Requirement

- [jse](https://github.com/liqa1024/jse/) (latest)

- C & C++ Compiler

  For Windows, [MSVC](https://visualstudio.microsoft.com/vs/features/cplusplus/) is recommended

  For Linux, [GCC](https://gcc.gnu.org/) is recommended


# Instruction

* `trainNNAP.groovy` training NNAP based on extended xyz format (ASE standard) files.
* `calEnergy.groovy` reading NNAP model to calculate energy through jse standard interface.
* `calEnergy.py` reading NNAP model to calculate energy in python through ASE calculator provided by jse.
* `runNNAP.in` running NNAP pair in LAMMPS.

All scripts are run through the `jse` command, for example:

```shell
jse trainNNAP.groovy
jse calEnergy.py
```

Include the LAMMPS in file:

```shell
mpiexec -np 1 jse -lmp -in runNNAP.in
```


# Citation

_TODO_


# License

Scripts and datas on this repository are licensed under the **GNU GPL v3**.
See [LICENSE](LICENSE) for details.

