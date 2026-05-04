/**
 * This file is part of example scripts of NNAP in jse
 * Copyright 2026 Qing'an Li
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

import jse.atom.data.DataXYZ
import jse.math.matrix.IMatrix
import jsex.nnap.NNAP


def nnap = new NNAP('nnpot/Cu.json')
def data = DataXYZ.read('data/Cu-test.xyz')

println('DFT energy: ' + data.parameter('energy'))
println('NNAP energy: ' + nnap.calEnergy(data))

println('DFT force: ' + ((IMatrix)data.property('forces')).row(0))
println('NNAP force: ' + nnap.calForces(data).row(0))

