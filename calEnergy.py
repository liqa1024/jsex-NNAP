#############################################################################
# This file is part of example scripts of NNAP in jse                       #
# Copyright 2025 Qing'an Li                                                 #
#                                                                           #
# This program is free software: you can redistribute it and/or modify      #
# it under the terms of the GNU General Public License as published by      #
# the Free Software Foundation, either version 3 of the License, or         #
# (at your option) any later version.                                       #
#                                                                           #
# This program is distributed in the hope that it will be useful,           #
# but WITHOUT ANY WARRANTY; without even the implied warranty of            #
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the             #
# GNU General Public License for more details.                              #
#                                                                           #
# You should have received a copy of the GNU General Public License         #
# along with this program.  If not, see <https://www.gnu.org/licenses/>.    #
#############################################################################

from ase.io import read
from jsex.nnap import NNAP

nnap_calc = NNAP('nnpot/Cu.json').asAseCalculator()
atoms_dft = read('data/Cu-test.xyz', '0', format='extxyz')

atoms_nnap = atoms_dft.copy()
atoms_nnap.calc = nnap_calc

print('DFT energy:', atoms_dft.get_potential_energy())
print('NNAP energy:', atoms_nnap.get_potential_energy())

print('DFT force:', atoms_dft.get_forces()[0, :])
print('NNAP force:', atoms_nnap.get_forces()[0, :])

